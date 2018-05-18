package de.codecentric.psd.worblehat.web.controller;

import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookAlreadyBorrowedException;
import de.codecentric.psd.worblehat.domain.BookService;
import de.codecentric.psd.worblehat.domain.Borrowing;
import de.codecentric.psd.worblehat.web.formdata.BookBorrowFormData;
import de.codecentric.psd.worblehat.web.formdata.SearchBorrowedBooksFormData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Controller for BorrowingBook
 */
@RequestMapping("/searchBorrowedBooks")
@Controller
public class SearchBorrowedBooksController {

	private BookService bookService;

	@Autowired
	public SearchBorrowedBooksController(BookService bookService) {
		this.bookService= bookService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void setupForm(final ModelMap model) {
		model.put("searchBorrowedBooksFormData", new SearchBorrowedBooksFormData());
	}

	
	
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(ModelMap modelMap, @ModelAttribute("searchBorrowedBooksFormData") @Valid SearchBorrowedBooksFormData searchBorrowedBooksFormData,
			BindingResult result) {
		
		List<Borrowing> borrowing = bookService.findBorrowedBooks(searchBorrowedBooksFormData.getEmail());
		modelMap.addAttribute("borrowing", borrowing);
		if (searchBorrowedBooksFormData.getEmail().isEmpty()) return "searchBorrowedBooks";
	    return "listBorrowedBooks";	
		
	}

	@ExceptionHandler(Exception.class)
	public String handleErrors(Exception ex, HttpServletRequest request) {
		return "home";
	}
}
