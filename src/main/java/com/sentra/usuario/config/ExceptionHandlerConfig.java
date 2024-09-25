package com.sentra.usuario.config;

import com.sentra.usuario.dto.Mensaje;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Mensaje> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        Mensaje mensaje = new Mensaje(errorMessage);
        return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    }

}
