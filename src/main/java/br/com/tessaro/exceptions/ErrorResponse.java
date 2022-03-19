package br.com.tessaro.exceptions;

import lombok.Getter;

@Getter
public class ErrorResponse {
	
	private String message;
	private Integer status_code;
	
	public ErrorResponse(Integer status_code, String message) {
		super();
		this.status_code = status_code;
		this.message = message;
	}

}
