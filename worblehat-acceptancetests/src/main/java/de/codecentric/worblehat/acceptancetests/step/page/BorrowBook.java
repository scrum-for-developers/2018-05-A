package de.codecentric.worblehat.acceptancetests.step.page;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.codecentric.psd.worblehat.domain.Borrowing;
import de.codecentric.psd.worblehat.domain.BorrowingRepository;
import de.codecentric.worblehat.acceptancetests.adapter.SeleniumAdapter;
import de.codecentric.worblehat.acceptancetests.adapter.wrapper.Page;
import de.codecentric.worblehat.acceptancetests.adapter.wrapper.PageElement;

@Component
public class BorrowBook {

	private SeleniumAdapter seleniumAdapter;
	private BorrowingRepository borrowingRepository;

	@Autowired
	public BorrowBook(SeleniumAdapter seleniumAdapter, BorrowingRepository borrowingRepository) {
		this.seleniumAdapter = seleniumAdapter;
		this.borrowingRepository = borrowingRepository;
	}

	// *******************
	// *** G I V E N *****
	// *******************

	// *****************
	// *** W H E N *****s
	// *****************

	@When("user $borrower borrows the book $isbn now")
	public void whenUseruserBorrowsTheBookisbn(String user, String isbn){
		seleniumAdapter.gotoPage(Page.BORROWBOOK);
		seleniumAdapter.typeIntoField("email", user);
		seleniumAdapter.typeIntoField("isbn", isbn);
		seleniumAdapter.clickOnPageElement(PageElement.BORROWBOOKBUTTON);
	}

	@When("user $borrower borrows the book $isbn on the date $date")
	public void whenUseruserBorrowsTheBookisbn(String borrower, String isbn, String date) throws ParseException {
		seleniumAdapter.gotoPage(Page.BORROWBOOK);
		seleniumAdapter.typeIntoField("email", borrower);
		seleniumAdapter.typeIntoField("isbn", isbn);
		seleniumAdapter.clickOnPageElement(PageElement.BORROWBOOKBUTTON);

		List<Borrowing> borrowings = borrowingRepository.findBorrowingsByBorrower(borrower);
		for (Borrowing borrowing : borrowings) {
			if(borrowing.getBorrowedBook().getIsbn().equals(isbn)) {
				borrowing.setBorrowDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
				borrowingRepository.saveAndFlush(borrowing);
			}
		}

	}

	// *****************
	// *** T H E N *****
	// *****************

	@Then("I get an error message $message when the borrower $borrower tries to borrow the book with isbn $isbn again")
	public void whenBorrowerBorrowsBorrowedBookShowErrorMessage(String message,
			String borrower,
			String isbn){
		seleniumAdapter.gotoPage(Page.BORROWBOOK);
		seleniumAdapter.typeIntoField("email", borrower);
		seleniumAdapter.typeIntoField("isbn", isbn);
		seleniumAdapter.clickOnPageElement(PageElement.BORROWBOOKBUTTON);
		String errorMessage = seleniumAdapter.getTextFromElement(PageElement.ISBNERROR);
		assertThat(errorMessage, is(message));
	}


}
