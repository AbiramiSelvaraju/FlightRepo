package com.demo.config;

import com.demo.exception.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisory extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ModelNotFoundException.class)
	public ResponseEntity<String> handleModelNotFoundException(ModelNotFoundException modelNotFoundException) {
		return new ResponseEntity<>(modelNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

}
