package com.bci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



public class ResourceAlreadyExistException extends Exception{

	private static final long serialVersionUID = 6811420689277734077L;

	public ResourceAlreadyExistException(String msg) {
		super(msg);
	}

}
