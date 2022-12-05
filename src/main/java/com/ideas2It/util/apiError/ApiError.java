package com.ideas2It.util.apiError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ideas2It.dto.TwoWheelerDto;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This class handle the exception 
 * These exceptions throws from controller
 *
 * @version 1.0
 * @author arunkumar
 */
@RestControllerAdvice
public class ApiError {
	Map<String, String> errorAndException = new HashMap<>();

	/**
	 * This method send response Entity
	 * When user give invalid input this method called and pass the message
	 * 
	 * @param ex
	 * @return responceEntity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> InvalidedEntry(MethodArgumentNotValidException ex) {
		errorAndException.clear();
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		errors.forEach(error -> errorAndException.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errorAndException, HttpStatus.BAD_REQUEST);	
	}
	
	/**
	 * This method send response Entity
	 * This method handle custom exception
	 * 
	 * @param ex
	 * @return responceEntity
	 */
	@ExceptionHandler(VehicleManagementException.class)
	public ResponseEntity<Map<String, String>> occuredCustomException(VehicleManagementException ex) {
		errorAndException.clear();
		errorAndException.put("Error message", ex.getMessage());
		return new ResponseEntity<>(errorAndException, HttpStatus.BAD_REQUEST);	
	}
}
