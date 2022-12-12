package com.example.denimshop.dto;


import com.example.denimshop.type.MaterialCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DenimDto {
    private final long denimId;
    private final String productCode;
    private final long price;
    private final MaterialCategory category;
    private final String image;
    private final String description;

    @Builder
    public DenimDto(long denimId, String productCode, long price, MaterialCategory category, String image, String description) {
        this.denimId = denimId;
        this.productCode = productCode;
        this.price = price;
        this.image = image;
        this.category = category;
        this.description = description;
    }
}
