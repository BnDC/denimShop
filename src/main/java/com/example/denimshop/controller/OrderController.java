package com.example.denimshop.controller;

import com.example.denimshop.dto.OrderDto;
import com.example.denimshop.dto.OrderRequest;
import com.example.denimshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/new")
    public ResponseEntity<Long> addOrderItem(
            @CookieValue(value = "token") String token,
            @Validated @RequestBody OrderRequest orderRequest
    ) {

        long orderId = orderService.createOrder(token, orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderDto>> getOrderList(
            @CookieValue(value = "token") String token
    ) {
        return new ResponseEntity<>(orderService.getAllOrder(token), HttpStatus.OK);
    }
}
