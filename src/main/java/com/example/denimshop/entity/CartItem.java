package com.example.denimshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "cart")
@NoArgsConstructor
public class CartItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "denim_id")
    private Denim denim;

    private long quantity;

    public CartItem(Member member, Denim denim, long quantity) {
        this.member = member;
        this.denim = denim;
        this.quantity = quantity;
    }

    public void setMember(Member member) {
        this.member = member;
        if (!member.getCartItemList().contains(this)) {
            member.getCartItemList().add(this);
        }
    }

    public void addQuantity() {
        ++quantity;
    }

    public void setDenim(Denim denim) {
        this.denim = denim;
    }

    public void changeQuantity(boolean isMinus) {
        if (isMinus) {
            this.quantity -= 1;
        } else {
            this.quantity += 1;
        }
    }

//    public boolean calculate(long requestQuantity) {
//        if (denim.getStock() < requestQuantity) {
//            return false;
//        }
//        return true;
//    }

    public OrderItem CartItemToOrderItem(Orders order) {
        if (denim.getStock() < quantity) {
            throw new RuntimeException("재고 부족");
        }
        return OrderItem.builder()
                .order(order)
                .denim(denim)
                .quantity(quantity)
                .build();
    }

}
