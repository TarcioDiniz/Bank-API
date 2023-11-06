package com.api.bank.BankApiLayer.exceptions.BankException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenericBADException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public GenericBADException(String exception) {
        super(exception);
    }

}
