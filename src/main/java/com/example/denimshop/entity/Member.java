package com.example.denimshop.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "member",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "emailUnique",
                        columnNames = {"email"})})
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "member")
    private List<CartItem> cartItemList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = LAZY)
    private List<Orders> ordersList = new ArrayList<>();

    @Builder
    Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addOrder(Orders order) {
        ordersList.add(order);
        if (order.getMember() != this) {
            order.setMember(this);
        }
    }
}
