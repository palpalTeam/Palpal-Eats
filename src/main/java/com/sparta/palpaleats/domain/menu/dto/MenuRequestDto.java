package com.sparta.palpaleats.domain.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class MenuRequestDto {
    private String name;
    private Integer price;
    private String category;
    private MultipartFile menuPicture;
}
