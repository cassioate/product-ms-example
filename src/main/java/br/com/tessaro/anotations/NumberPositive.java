package br.com.tessaro.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.tessaro.anotations.validators.NumberPositiveValidator;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NumberPositiveValidator.class})
public @interface NumberPositive {

	String message() default "Números negativos não são permitidos, por favor insira um valor valido.";
	 
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}