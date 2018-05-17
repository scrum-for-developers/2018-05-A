<<<<<<< HEAD
package de.codecentric.psd.worblehat.web.validation;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.ISBNValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ISBNConstraintValidator implements ConstraintValidator<ISBN, String> {

	@Override
	public void initialize(ISBN constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// Don't validate null, empty and blank strings, since these are validated by @NotNull, @NotEmpty and @NotBlank
		if(StringUtils.isNotBlank(value)) {
			return (ISBNValidator.getInstance().isValidISBN10(value) ||
					ISBNValidator.getInstance().isValidISBN13(value) );
		}
		return true;
	}

}
=======
package de.codecentric.psd.worblehat.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.ISBNValidator;

public class ISBNConstraintValidator implements ConstraintValidator<ISBN, String> {

	@Override
	public void initialize(ISBN constraintAnnotation) {
		// nop
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// Don't validate null, empty and blank strings, since these are validated by @NotNull, @NotEmpty and @NotBlank
		if(StringUtils.isNotBlank(value))
			return ISBNValidator.getInstance().isValidISBN10(value);
		return true;
	}

}
>>>>>>> 8a5d9703f11d8efb2dc84f2ea668c3d9fe35c502
