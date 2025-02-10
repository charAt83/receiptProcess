package com.fetch.receiptProcessor.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomException {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ReceiptNotFoundException.class)
	public ResponseEntity<Object> handleReceiptNotFoundException(ReceiptNotFoundException e) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", e.getMessage());
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);

	}
}