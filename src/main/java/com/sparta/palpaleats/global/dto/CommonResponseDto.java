package com.sparta.palpaleats.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonResponseDto {
    private Integer status;
    private String msg;
}
