package com.api.bank.BankApiLayer.exceptions.handler;

import com.api.bank.BankApiLayer.exceptions.BankException.GenericBADException;
import com.api.bank.BankApiLayer.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // Tratamento de exceções mais genéricas possíveis.
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(
            Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), exception.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



     // PARA OUTROS TIPOS DE TRATAMENTO DE EXCEÇÕES MAIS EXCLUSIVA
     // PASSE COMO PARÂMETRO DE "@ExceptionHandler" SUA classe.class
     // NÃO ESQUEÇA DE ALTERAR O NOME DO MÉTODO

    @ExceptionHandler(GenericBADException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(
            Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), exception.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }



}
