package com.example.denimshop.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem extends BaseTimeEntity {
    @Id
    @Column(name = "order_item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "denim_id")
    private Denim denim;

    @Column(nullable = false)
    private long quantity;

    @Builder
    public OrderItem(Orders order, Denim denim, long quantity) {
        this.order = order;
        this.denim = denim;
        this.quantity = quantity;
    }

    public void setOrder(Orders order) {
        this.order = order;
        if (!order.getOrderItemList().contains(this)) {
            order.getOrderItemList().add(this);
        }
    }

    public void setDenim(Denim denim) {
        this.denim = denim;
    }

}
