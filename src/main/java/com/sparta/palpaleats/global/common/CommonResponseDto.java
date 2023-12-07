package com.sparta.palpaleats.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@NoArgsConstructor
@Getter
public class CommonResponseDto {
    private Integer status;
    private String msg;

    public CommonResponseDto(CommonResponseCode code) {
        this.status = code.getHttpStatus().value();
        this.msg = code.getMessage();
    }
}
