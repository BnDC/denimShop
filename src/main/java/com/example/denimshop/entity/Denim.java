package com.example.denimshop.entity;

import com.example.denimshop.type.MaterialCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

@Entity
@Getter
@Table(name = "denim")
@NoArgsConstructor
public class Denim extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long denimId;

    @Column
    private String productCode;

    @Column
    private long price;

    @Column
    @Enumerated(EnumType.STRING)
    private MaterialCategory category;

    @Column
    private String description;

    @Column
    private long stock;

    @Column
    private String image;

    @Override
    public String toString() {
        return MessageFormat.format(
                "Denim'{'denimId={0}, productCode=''{1}'', price={2}, category={3}, description=''{4}'''}'",
                denimId, productCode, price, category, description
        );
    }

    @Builder
    Denim(long denimId, String productCode, long price, MaterialCategory category, String description, String image, long stock) {
        this.denimId = denimId;
        this.productCode = productCode;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        this.stock = stock;
    }

    @Builder
    Denim(String productCode, long price, MaterialCategory category, String description, String image, long stock) {
        this.productCode = productCode;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        this.stock = stock;
    }
}
