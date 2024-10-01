package com.emerald.ShortLink.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Captura quando não passa o valor esperado na URL
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        var error = new ErrorResponse(HttpStatus.NOT_FOUND, "A URL que você está tentando acessar não existe ou está incorreta.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        var error = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "O método HTTP utilizado não é suportado para esta rota. Esqueceu de implementar alguma variável na URL?");
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }
}

