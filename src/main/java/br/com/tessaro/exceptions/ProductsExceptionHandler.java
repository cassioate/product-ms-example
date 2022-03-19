package br.com.tessaro.exceptions;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.tessaro.exceptions.business.NotFindProductByIdException;
import br.com.tessaro.exceptions.business.NotPossibleMakeTheUpdateException;

@ControllerAdvice
public class ProductsExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (MethodArgumentNotValidException ex) {
		ErrorResponse error = new ErrorResponse(400, "Bad Request: Invalid fields");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (ValidationException ex) {
		ErrorResponse error = new ErrorResponse(400, "Bad Request: You forgot to fill a field");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (HttpMessageNotReadableException ex) {
		ErrorResponse error = new ErrorResponse(400, "Bad Request: One or more fields are filled in incorrectly");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(NotPossibleMakeTheUpdateException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (NotPossibleMakeTheUpdateException ex) {
		ErrorResponse error = new ErrorResponse(404, "Not Found: Can't find any product with this ID");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(NotFindProductByIdException.class)
	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (NotFindProductByIdException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
//
//@ControllerAdvice
//public class ProductsExceptionHandler {
//	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (MethodArgumentNotValidException ex) {
//		ErrorResponse error = new ErrorResponse(400, "Bad Request: Invalid fields" , ex.getMessage());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//	}
//
//	@ExceptionHandler(ValidationException.class)
//	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (ValidationException ex) {
//		ErrorResponse error = new ErrorResponse(400, "Bad Request: You forgot to fill a field" , ex.getMessage());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//	}
//
//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	public final ResponseEntity<ErrorResponse> handleBadRequestArgument (HttpMessageNotReadableException ex) {
//		ErrorResponse error = new ErrorResponse(400, "Bad Request: One or more fields are filled in incorrectly" , ex.getMessage());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//	}
//	
//}