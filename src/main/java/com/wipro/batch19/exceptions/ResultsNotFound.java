package com.wipro.batch19.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Search results not found")
public class ResultsNotFound extends RuntimeException {
	
	public ResultsNotFound(String message) {
		System.out.println(message);
	}
	

}
