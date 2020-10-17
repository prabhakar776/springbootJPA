package com.wipro.batch19.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvise {
	
	@ExceptionHandler()
	public ResponseEntity<CustomerErrorResponse> handleNotFoundException (NotFoundException e) {
		
		CustomerErrorResponse error = new CustomerErrorResponse("NOT_FOUND_ERROR", e.getErrorMsg());
		error.setTimeStamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	

}
