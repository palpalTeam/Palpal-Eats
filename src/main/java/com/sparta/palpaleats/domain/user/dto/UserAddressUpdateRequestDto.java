package com.sparta.palpaleats.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressUpdateRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s-]+$")
    private String address;
}
