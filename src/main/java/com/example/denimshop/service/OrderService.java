package com.example.denimshop.service;

import com.example.denimshop.dto.DtoMapper;
import com.example.denimshop.dto.OrderDto;
import com.example.denimshop.dto.OrderRequest;
import com.example.denimshop.entity.Member;
import com.example.denimshop.entity.Orders;
import com.example.denimshop.repository.CartRepository;
import com.example.denimshop.repository.MemberRepository;
import com.example.denimshop.repository.OrderRepository;
import com.example.denimshop.type.OrderStatus;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public long createOrder(String token, OrderRequest orderRequest) {
        if (!jwtService.isValidToken(token)) {
            throw new JwtException("");
        }
        long memberId = jwtService.getId(token);
        Member member = memberRepository.findById(memberId).get();

        Orders order = new Orders(OrderStatus.PAYMENT, orderRequest.getAddress(), orderRequest.getOrderer());
        order.setMember(member);

        member.getCartItemList()
                .stream()
                .map((cartItem -> cartItem.CartItemToOrderItem(order)))
                .forEach(order::addOrderItem);

        cartRepository.deleteAll(member.getCartItemList());
        orderRepository.save(order);

        return order.getOrderId();
    }

    @Transactional
    public List<OrderDto> getAllOrder(String token) {
        if (!jwtService.isValidToken(token)) {
            throw new JwtException("");
        }
        Member member = memberRepository.findById(jwtService.getId(token)).get();
        List<Orders> ordersList = member.getOrdersList();

        log.info("" + member.getMemberId());
        log.info(member.getEmail());
        log.info(ordersList.toString());

        List<OrderDto> orderDtos = ordersList.stream()
                .map(DtoMapper::orderToOrderDto)
                .toList();

        System.out.println(orderDtos.toString());
        System.out.println(orderDtos.get(0).getAddress());

        return ordersList.stream()
                .map(DtoMapper::orderToOrderDto)
                .toList();
    }
}
