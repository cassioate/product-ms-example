package br.com.tessaro.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.tessaro.exceptions.business.NotFindProductByIdException;
import br.com.tessaro.exceptions.business.NotPossibleMakeTheUpdateException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ProductsExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<List<ErrorResponse>> handleBadRequestArgument (MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errosValidator(ex.getBindingResult(), 400));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (HttpMessageNotReadableException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(400, messageSource.getMessage("fields.incorrectly", null, LocaleContextHolder.getLocale())));
	}
	
	@ExceptionHandler(NotPossibleMakeTheUpdateException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (NotPossibleMakeTheUpdateException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(404, messageSource.getMessage("cant.find.product", null, LocaleContextHolder.getLocale())));
	}

	@ExceptionHandler(NotFindProductByIdException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (NotFindProductByIdException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.build();
	}

	public List<ErrorResponse> errosValidator (BindingResult ex, Integer status) {
		List<ErrorResponse> error = new ArrayList<>();
		for (ObjectError err: ex.getAllErrors()) {
			error.add(new ErrorResponse(status, err.getDefaultMessage()));
		}
		return error;
	}
}
