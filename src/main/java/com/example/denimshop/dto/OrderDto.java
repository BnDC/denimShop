package com.example.denimshop.dto;

import com.example.denimshop.type.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDto {
    private OrderStatus orderStatus;
    private String address;
    private String orderer;
    private List<OrderItemDto> orderItemDtos;

    @Builder
    public OrderDto(OrderStatus orderStatus, String address, String orderer, List<OrderItemDto> orderItemDtos) {
        this.orderStatus = orderStatus;
        this.address = address;
        this.orderer = orderer;
        this.orderItemDtos = orderItemDtos;
    }
}
