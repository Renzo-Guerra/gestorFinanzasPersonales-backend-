package com.gestionFinanzasPersonales.gestionFinanzasPersonales.Exceptions;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.Exceptions.Exceptions.EmailYaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EmailYaExisteException.class)
    public ResponseEntity<?> emailYaExiste(EmailYaExisteException exception){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
