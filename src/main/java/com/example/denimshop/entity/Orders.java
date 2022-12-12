package com.example.denimshop.entity;

import com.example.denimshop.type.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Orders extends BaseTimeEntity {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(length = 100, nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 10, nullable = false)
    private String orderer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>();

    public Orders(OrderStatus orderStatus, String address, String orderer) {
        this.orderStatus = orderStatus;
        this.address = address;
        this.orderer = orderer;
    }

    public void setMember(Member member) {
        this.member = member;
        if (!member.getOrdersList().contains(this)) {
            member.addOrder(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        if (orderItem.getOrder() == null) {
            orderItem.setOrder(this);
        }
    }
}
