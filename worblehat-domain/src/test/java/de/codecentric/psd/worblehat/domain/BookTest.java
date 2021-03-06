package de.codecentric.psd.worblehat.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTest {

    Book BOOK;

    @Before
    public void setup() {
        BOOK = new Book("Titel", "Author", "2", "1", "Gutes Buch!", 1234);
    }

    @Test
    public void shouldReturnFalseWhenAuthorIsDifferent() {
        Book anotherCopy = new Book(BOOK.getTitle(), BOOK.getAuthor(), BOOK.getEdition(), BOOK.getIsbn(), BOOK.getDescription(), BOOK.getYearOfPublication());
        anotherCopy.setAuthor("Bene");
        assertThat(BOOK.isSameCopy(anotherCopy), is(false));
    }

    @Test
    public void shouldReturnFalseWhenTitleIsDifferent() {
        Book anotherCopy = new Book(BOOK.getTitle(), BOOK.getAuthor(), BOOK.getEdition(), BOOK.getIsbn(), BOOK.getDescription(),  BOOK.getYearOfPublication());
        anotherCopy.setTitle("Lord of the Rings");
        assertThat(BOOK.isSameCopy(anotherCopy), is(false));
    }
    
    @Test
    public void shouldReturnFalseWhenEditionIsDifferent() {
        Book anotherCopy = new Book(BOOK.getTitle(), BOOK.getAuthor(), BOOK.getEdition(), BOOK.getIsbn(), BOOK.getDescription(),  BOOK.getYearOfPublication());
        anotherCopy.setEdition("3rd");
        assertThat(BOOK.isSameCopy(anotherCopy), is(false));
    }
    

    @Test
    public void shouldReturnTrueWhenAllButTitleAndAuthorAndEditionAreDifferent() {
        Book anotherCopy = new Book(BOOK.getTitle(), BOOK.getAuthor(), BOOK.getEdition(), BOOK.getIsbn(), BOOK.getDescription(),  BOOK.getYearOfPublication());
        anotherCopy.setIsbn("123456789X");
        anotherCopy.setYearOfPublication(2010);
        assertThat(BOOK.isSameCopy(anotherCopy), is(true));
    }

    @Test
    public void shouldBeBorrowable() {
        BOOK.borrowNowByBorrower("a@bc.de");
        assertThat(BOOK.getBorrowing().getBorrowerEmailAddress(), is("a@bc.de"));
    }

    @Test
    public void shouldIgnoreNewBorrowWhenBorrowed() {
        BOOK.borrowNowByBorrower("a@bc.de");
        BOOK.borrowNowByBorrower("a@bc.ru");
        assertThat(BOOK.getBorrowing().getBorrowerEmailAddress(), is("a@bc.de"));
    }
}