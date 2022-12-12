package com.example.denimshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderRequest {

    @NotBlank
    private final String address;

    @NotBlank
    private final String orderer;

    public OrderRequest(String address, String orderer) {
        this.address = address;
        this.orderer = orderer;
    }
}
