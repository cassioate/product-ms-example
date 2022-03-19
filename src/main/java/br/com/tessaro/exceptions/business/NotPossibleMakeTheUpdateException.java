package br.com.tessaro.exceptions.business;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotPossibleMakeTheUpdateException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public NotPossibleMakeTheUpdateException(String msg) {
		super(msg);
	}
	
	public NotPossibleMakeTheUpdateException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}


