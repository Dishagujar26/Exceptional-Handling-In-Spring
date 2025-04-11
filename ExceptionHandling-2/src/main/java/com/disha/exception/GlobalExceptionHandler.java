package com.disha.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/*
	@ControllerAdvice is designed to intercept exceptions globally for controllers, but it does not assume that the returned object should be sent as JSON.

    Spring MVC assumes that methods in @ControllerAdvice return a view name by default unless explicitly told otherwise.

    Since ErrorResponse is not a view name, Spring will try to resolve it as a view, which leads to unintended behavior.
   */
    

	@ExceptionHandler(value = NoSuchEmployeeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNoSuchEmpExistsException(NoSuchEmployeeException ex) {
	     return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = EmployeeAlreadyExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorResponse handleEmployeeAlreadyExistsException(EmployeeAlreadyExistException ex) {
	     return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
	//used by both add and update method 
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		//entity class's fields will be my key and the messages in the annotation will be my value 
		Map<String , String> errorMap = new HashMap<>();
		BindingResult bindingResultEx = ex.getBindingResult();
		//list if errors 
		List<FieldError> errorList = bindingResultEx.getFieldErrors();
		for(FieldError e:errorList) {
			errorMap.put(e.getField(), e.getDefaultMessage());
		}
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	//a universal handler to catch any exception, safer side cause parent class is Exception
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);//500
	}

}
