package com.example.denimshop.dto;

import lombok.Getter;

@Getter
public class CartListDto {
    private final DenimDto denimDto;
    private final long quantity;

    public CartListDto(DenimDto denimDto, long quantity) {
        this.denimDto = denimDto;
        this.quantity = quantity;
    }
}
