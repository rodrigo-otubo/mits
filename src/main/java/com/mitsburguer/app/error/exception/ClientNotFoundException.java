package com.mitsburguer.app.error.exception;

import lombok.Getter;

public class ClientNotFoundException extends RuntimeException{
    @Getter
    private String errorCode = "ERR-02";

}
