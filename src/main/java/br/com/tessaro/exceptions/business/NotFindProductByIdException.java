package br.com.tessaro.exceptions.business;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFindProductByIdException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public NotFindProductByIdException(String msg) {
		super(msg);
	}
	
	public NotFindProductByIdException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}


