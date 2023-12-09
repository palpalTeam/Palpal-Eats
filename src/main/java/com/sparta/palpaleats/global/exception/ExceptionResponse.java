package com.sparta.palpaleats.global.exception;
import lombok.Getter;

@Getter
public class ExceptionResponse {

    private final Integer status;
    private final String msg;

    public ExceptionResponse(ExceptionCode exceptionCode) {
        this.status = exceptionCode.getHttpStatus().value();
        this.msg = exceptionCode.getMessage();
    }
}
