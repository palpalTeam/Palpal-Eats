package com.sparta.palpaleats.domain.menu.dto;

import com.sparta.palpaleats.domain.menu.entity.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuResponseDto {
    private String name;
    private String category;
    private String storePictureUrl;

    public MenuResponseDto(Menu menu){
        this.name = menu.getName();
        this.category = menu.getCategory();
        this.storePictureUrl = menu.getMenuPictureUrl();
    }
}
