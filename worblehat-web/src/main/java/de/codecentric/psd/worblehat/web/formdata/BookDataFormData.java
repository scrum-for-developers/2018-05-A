package de.codecentric.psd.worblehat.web.formdata;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import de.codecentric.psd.worblehat.web.validation.ISBN;
import de.codecentric.psd.worblehat.web.validation.Numeric;

/**
 * This class represent the form data of the add book form.
 */
public class BookDataFormData {

	@NotEmpty(message = "{empty.bookDataFormData.title}")
	private String title;

	@NotEmpty(message = "{empty.bookDataFormData.edition}")
	@Numeric(message = "{notvalid.bookDataFormData.edition}")
	private String edition;

	@NotEmpty(message = "{empty.bookDataFormData.yearOfPublication}")
	@Numeric(message = "{notvalid.bookDataFormData.yearOfPublication}")
	@Length(message = "{invalid.length.bookDataFormData.yearOfPublication}", min = 4, max = 4)
	private String yearOfPublication;

	@NotEmpty(message = "{empty.bookDataFormData.isbn}")
	@ISBN(message = "{notvalid.bookDataFormData.isbn}")
	private String isbn;

	@NotEmpty(message = "{empty.bookDataFormData.author}")
	private String author;

	@NotEmpty(message = "{empty.bookDataFormData.description}")
	private String description;

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(String yearOfPublication) {
		this.yearOfPublication = yearOfPublication.trim();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn.trim();
	}

	public String getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public void setAuthor(String author) {
		this.author = author.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition.trim();
	}

	@Override
	public String toString() {
		return "BookDataFormData [title=" + title + ", edition=" + edition
				+ ", yearOfPublication=" + yearOfPublication + ", isbn=" + isbn + ", author=" + author
				+ "]";
	}

}
