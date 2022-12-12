package com.example.denimshop.dto;

import lombok.Getter;


@Getter
public class CartUpdateRequest {
    private final String token;
    private final long denimId;
    private final boolean isMinus;

    public CartUpdateRequest(String token, long itemId, boolean isMinus) {
        this.token = token;
        this.denimId = itemId;
        this.isMinus = isMinus;
    }
}
