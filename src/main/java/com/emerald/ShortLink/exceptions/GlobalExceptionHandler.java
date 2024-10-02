package com.emerald.ShortLink.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Link Curto não encontrado
    @ExceptionHandler(ShortLinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ErrorResponse> shortLinkNotFound(ShortLinkNotFoundException ex) {
        var error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Trata JSON malformado ou não legível, incluindo corpo ausente
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        var error = new ErrorResponse(HttpStatus.BAD_REQUEST ,"O corpo da requisição está ausente ou malformado. Por favor, verifique e tente novamente.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Captura quando não passa o valor esperado na URL
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        var error = new ErrorResponse(HttpStatus.NOT_FOUND, "A URL que você está tentando acessar não existe ou está incorreta.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Requisições não suportadas
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        var error = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "O método HTTP utilizado não é suportado para esta rota. Esqueceu de implementar alguma variável na URL?");
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Falta de variáveis no body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        var error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Estrutura do body da requisição inválida.");

        // Você pode personalizar a estrutura da resposta conforme necessário
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Tratar outras exceções
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // Log da exceção para o desenvolvedor
        ex.printStackTrace();

        var error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");

        // Resposta genérica para o usuário
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

