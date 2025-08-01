package za.co.ashtech.stroller.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import za.co.ashtech.stroller.controller.entities.ErrorResponse;
import za.co.ashtech.stroller.util.StrollerServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StrollerServiceException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(StrollerServiceException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"An unexpected error occurred", LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
