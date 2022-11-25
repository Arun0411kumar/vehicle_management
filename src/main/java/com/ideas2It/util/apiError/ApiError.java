package com.ideas2It.util.apiError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ideas2It.util.customException.VehicleManagementException;

@RestControllerAdvice
public class ApiError {
	Map<String, String> errorAndException = new HashMap<>();

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> InvalidedEntry(MethodArgumentNotValidException ex) {
		errorAndException.clear();
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		errors.forEach(error -> errorAndException.put(error.getField(), error.getDefaultMessage()));
		return errorAndException;	
	}
	
	@ExceptionHandler(VehicleManagementException.class)
	public Map<String, String> occuredCustomException(VehicleManagementException ex) {
		errorAndException.clear();
		errorAndException.put("Error message", ex.getMessage());
		return errorAndException;	
	}
}
