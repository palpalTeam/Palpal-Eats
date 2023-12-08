package com.sparta.palpaleats.domain.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class StoreRequestDto {

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^[가-힣]*$")
    private String category;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9가-힣-]+$")
    private String address;

    @NotNull
    private Integer minDeliveryPrice;

    private MultipartFile storePicture;

    @NotNull
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$\n")
    private String phone;

    private String content;

    private boolean openStatus;
}
