package com.jump.controller.advice;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jump.exception.VisitIdMismatchException;
import com.jump.exception.VisitNotFoundException;

@RestControllerAdvice
public class VisitControllerAdvice {

	@ExceptionHandler(VisitNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleVisitNotFound(VisitNotFoundException e) {
		ExceptionResponse response = new ExceptionResponse("Visit Not Found!", "Visit-404", LocalDate.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(VisitIdMismatchException.class)
	public ResponseEntity<ExceptionResponse> handleVisitIdMismatch(VisitIdMismatchException e) {
		ExceptionResponse response = new ExceptionResponse("ID in path and Visit body do not match", "Visit-400", LocalDate.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
