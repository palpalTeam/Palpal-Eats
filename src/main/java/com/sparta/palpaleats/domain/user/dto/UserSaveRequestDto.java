package com.sparta.palpaleats.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+-_.]{8,15}$")
    private String password;

    @NotNull
    private Boolean isSeller;

    @NotBlank
    private String nickname;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s-]+$")
    private String currentAddress;
}
