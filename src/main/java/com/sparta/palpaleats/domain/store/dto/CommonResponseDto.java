package com.sparta.palpaleats.domain.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

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
