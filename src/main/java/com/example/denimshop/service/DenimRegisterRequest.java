package com.example.denimshop.service;

import com.example.denimshop.type.MaterialCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class DenimRegisterRequest {

    @NotBlank
    private final String productCode;

    @Positive
    private final long price;

    private final MaterialCategory category;
    private final String description;

    public DenimRegisterRequest(String productCode, long price, MaterialCategory category, String description) {
        this.productCode = productCode;
        this.price = price;
        this.category = category;
        this.description = description;
    }
}
