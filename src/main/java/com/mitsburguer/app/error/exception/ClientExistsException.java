package com.mitsburguer.app.error.exception;

import lombok.Getter;

public class ClientExistsException extends RuntimeException{
    @Getter
    private String errorCode = "ERR-01";

}
