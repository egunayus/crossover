package com.crossover.techtrial.api.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler that can be used to customise error messages 
 * and HTTP status codes before returning the response to the client
 * 
 * @author egunay
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class) 
	public void handleIllegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
	}
	  
}
