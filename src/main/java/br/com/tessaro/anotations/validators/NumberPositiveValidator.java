package br.com.tessaro.anotations.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.tessaro.anotations.NumberPositive;
import br.com.tessaro.domain.dto.ProductDTO;

public class NumberPositiveValidator implements ConstraintValidator<NumberPositive, ProductDTO>{

	@Override
	public boolean isValid(ProductDTO value, ConstraintValidatorContext context) {
		if (value.getPrice() != null && value.getPrice().signum() == -1) {
			return false;
		}
		return true;
	}
}
