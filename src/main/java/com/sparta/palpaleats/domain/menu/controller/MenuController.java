package com.sparta.palpaleats.domain.menu.controller;

import com.sparta.palpaleats.domain.menu.dto.MenuRequestDto;
import com.sparta.palpaleats.domain.menu.dto.MenuResponseDto;
import com.sparta.palpaleats.domain.menu.service.MenuService;
import com.sparta.palpaleats.global.common.CommonResponseDto;
import com.sparta.palpaleats.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/{storeId}/menu")
    public ResponseEntity<CommonResponseDto> addMenu(@ModelAttribute MenuRequestDto requestDto,
                                                     @PathVariable Long storeId,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) throws UnsupportedEncodingException {
        CommonResponseDto commonResponseDto = menuService.addMenu(requestDto, storeId, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @GetMapping("/{storeId}/menu")
    public ResponseEntity<List<MenuResponseDto>> getMenuList(@PathVariable Long storeId){
        List<MenuResponseDto> menuResponseDtoList = menuService.getMenuList(storeId);
        return ResponseEntity.ok().body(menuResponseDtoList);
    }

    @GetMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long storeId, @PathVariable Long menuId){
        MenuResponseDto menuResponseDto = menuService.getMenu(storeId, menuId);
        return ResponseEntity.ok().body(menuResponseDto);
    }

    @PatchMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> updateMenuPicture(@PathVariable Long storeId,
                                                               @PathVariable Long menuId,
                                                               @RequestParam("file") MultipartFile multipartFile,
                                                               @AuthenticationPrincipal UserDetailsImpl userDetails) throws UnsupportedEncodingException {
        CommonResponseDto commonResponseDto = menuService.updateMenuPicture(storeId,menuId, multipartFile, userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> updateMenuName(@PathVariable Long storeId,
                                                            @PathVariable Long menuId,
                                                            @RequestParam("name") String name,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        CommonResponseDto commonResponseDto = menuService.updateMenuName(storeId, menuId, name, userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> updateMenuPrice(@PathVariable Long storeId,
                                                             @PathVariable Long menuId,
                                                             @RequestParam("price") Integer price,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        CommonResponseDto commonResponseDto = menuService.updateMenuPrice(storeId, menuId, price, userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> updateMenuCategory(@PathVariable Long storeId,
                                                                @PathVariable Long menuId,
                                                                @RequestParam("category") String category,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        CommonResponseDto commonResponseDto = menuService.updateMenuCategory(storeId, menuId, category, userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @DeleteMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> deleteMenu(@PathVariable Long storeId,
                                                        @PathVariable Long menuId,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        CommonResponseDto commonResponseDto = menuService.deleteMenu(storeId, menuId, userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

}