package com.bci.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bci.exception.ResourceAlreadyExistException;
import com.bci.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class CustomExceptionHandler{
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> map =  new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return map;

    }


	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put("mensaje", exception.getMessage());
        return map;
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public Map<String, String> handleResourceAlreadyExistException(ResourceAlreadyExistException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put("mensaje", exception.getMessage());
        return map;
    }

}
