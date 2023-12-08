package com.sparta.palpaleats.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNicknameUpdateRequestDto {

    @NotBlank
    String nickname;
}
