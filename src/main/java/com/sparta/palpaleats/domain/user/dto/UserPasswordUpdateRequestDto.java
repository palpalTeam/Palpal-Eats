package com.sparta.palpaleats.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordUpdateRequestDto {

    String oldPassword;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+-_.]{8,15}$")
    String newPassword;

}
