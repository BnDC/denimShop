package com.example.denimshop.dto;

import com.example.denimshop.entity.CartItem;
import com.example.denimshop.entity.Denim;
import com.example.denimshop.entity.OrderItem;
import com.example.denimshop.entity.Orders;

import java.util.stream.Collectors;

public class DtoMapper {
    public static DenimDto denimToDenimDto(Denim denim) {
        return DenimDto.builder()
                .denimId(denim.getDenimId())
                .productCode(denim.getProductCode())
                .category(denim.getCategory())
                .price(denim.getPrice())
                .image(denim.getImage())
                .description(denim.getDescription())
                .build();
    }

    public static CartListDto cartToCartListDto(CartItem cart) {
        return new CartListDto(denimToDenimDto(cart.getDenim()), cart.getQuantity());
    }

    public static OrderDto orderToOrderDto(Orders orders) {
        return OrderDto.builder()
                .orderStatus(orders.getOrderStatus())
                .orderer(orders.getOrderer())
                .address(orders.getAddress())
                .orderItemDtos(
                        orders.getOrderItemList()
                                .stream()
                                .map(DtoMapper::orderItemToOrderItemDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static OrderItemDto orderItemToOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(denimToDenimDto(orderItem.getDenim()), orderItem.getQuantity());
    }
}
