package de.codecentric.psd.worblehat.web.formdata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BookDataFormDataTest {

	@Test
	public void shouldTrimWhitespaces() {
		BookDataFormData data = new BookDataFormData();
		data.setAuthor("Autor   ");

		assertEquals("Autor", data.getAuthor());
	}

}
