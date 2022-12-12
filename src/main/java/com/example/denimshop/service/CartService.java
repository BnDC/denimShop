package com.example.denimshop.service;

import com.example.denimshop.dto.CartListDto;
import com.example.denimshop.dto.CartPushRequest;
import com.example.denimshop.dto.CartUpdateRequest;
import com.example.denimshop.dto.DtoMapper;
import com.example.denimshop.entity.CartItem;
import com.example.denimshop.entity.Denim;
import com.example.denimshop.entity.Member;
import com.example.denimshop.repository.CartRepository;
import com.example.denimshop.repository.DenimRepository;
import com.example.denimshop.repository.MemberRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final DenimRepository denimRepository;
    private final JwtService jwtService;

    @Transactional
    public void pushCartItem(CartPushRequest cartRequest) {
        if (!jwtService.isValidToken(cartRequest.getToken())) {
            throw new JwtException("");
        }
        long memberId = jwtService.getId(cartRequest.getToken());
        Member member = memberRepository.findById(memberId).get();
        Denim denim = denimRepository.findById(cartRequest.getDenimId()).get();

        List<CartItem> cartItemList = member.getCartItemList();
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getDenim() == denim) {
                cartItem.addQuantity();
                cartRepository.save(cartItem);
                return;
            }
        }
        CartItem cartItem = new CartItem(member, denim, 1);
        cartRepository.save(cartItem);
    }

    @Transactional
    public List<CartListDto> getCartItems(String token) {
        if (!jwtService.isValidToken(token)) {
            throw new JwtException("");
        }
        long memberId = jwtService.getId(token);
        Member member = memberRepository.findById(memberId).get();

        return member.getCartItemList()
                .stream()
                .map(DtoMapper::cartToCartListDto)
                .toList();
    }

    @Transactional
    public void updateCartItems(CartUpdateRequest cartUpdateRequest) {
        if (!jwtService.isValidToken(cartUpdateRequest.getToken())) {
            throw new JwtException("");
        }
        long memberId = jwtService.getId(cartUpdateRequest.getToken());
        Member member = memberRepository.findById(memberId).get();

        List<CartItem> cartItemList = member.getCartItemList();
        Denim targetDenim = denimRepository.findById(cartUpdateRequest.getDenimId()).get();

        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getDenim() == targetDenim) {
                cartItemList.get(i).changeQuantity(cartUpdateRequest.isMinus());
                if (cartItemList.get(i).getQuantity() == 0) {
                    cartRepository.delete(cartItemList.get(i));
                } else {
                    cartRepository.save(cartItemList.get(i));
                }
                break;
            }
        }


    }
}
