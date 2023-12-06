package com.sparta.palpaleats.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Setter
@NoArgsConstructor
@Getter
public class CommonResponseDto {
    private HttpStatus status;
    private String msg;

    public CommonResponseDto(CommonResponseCode code) {
        this.status = code.getHttpStatus();
        this.msg = code.getMessage();
    }
}
