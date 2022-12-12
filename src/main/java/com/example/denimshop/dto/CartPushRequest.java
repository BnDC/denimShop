package com.example.denimshop.dto;

import lombok.Getter;


@Getter
public class CartPushRequest {
    private final String token;
    private final long denimId;

    public CartPushRequest(String token, long itemId) {
        this.token = token;
        this.denimId = itemId;
    }
}
