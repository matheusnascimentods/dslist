package com.devsuperior.devlist.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.devlist.exception.dto.ErrorResponse;
import com.devsuperior.devlist.exception.exceptions.GameNotFoundException;
import com.devsuperior.devlist.exception.exceptions.GameListNotFoundException;

@ControllerAdvice
public class GameExceptionHandler {

	
   @ExceptionHandler(MethodArgumentNotValidException.class) 
   public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException exception) {
	   Map<String, String> errors = new HashMap<>();
	
	   exception.getBindingResult().getFieldErrors().forEach(error -> 
	    errors.put(error.getField(), error.getDefaultMessage()));
	
	   return ResponseEntity.unprocessableEntity().body(errors);
   }
   
   @ExceptionHandler(HttpMessageNotReadableException.class)
   public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
       ErrorResponse error = new ErrorResponse(
           "Invalid JSON: " + exception.getLocalizedMessage(),
           HttpStatus.BAD_REQUEST.value(),
           LocalDateTime.now()
       );
       return ResponseEntity.badRequest().body(error);
   }
   
   @ExceptionHandler({GameNotFoundException.class, GameListNotFoundException.class})
   public ResponseEntity<ErrorResponse> handleNotFoundException(Exception exception) {
       ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(),  LocalDateTime.now());
       return ResponseEntity.status(error.getStatus()).body(error);
   }
}
