package com.blinkspace.springjpa.controllers.exceptions;

import com.blinkspace.springjpa.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice //anotação para interceptar as exceções que acontecerem, para que esse objeto possa executar o possível tratamento
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //anotação para interceptar
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
