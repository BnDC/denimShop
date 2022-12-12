package com.example.denimshop.dto;

import lombok.Getter;

@Getter
public class OrderItemDto {
    private DenimDto denimDto;
    private long quantity;

    public OrderItemDto(DenimDto denimDto, long quantity) {
        this.denimDto = denimDto;
        this.quantity = quantity;
    }
}
