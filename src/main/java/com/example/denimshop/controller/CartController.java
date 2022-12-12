package com.example.denimshop.controller;

import com.example.denimshop.dto.CartListDto;
import com.example.denimshop.dto.CartPushRequest;
import com.example.denimshop.dto.CartUpdateRequest;
import com.example.denimshop.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/new/denims/{denimId}")
    public ResponseEntity<Void> pushCartItem(
            @CookieValue(value = "token", required = false) String token,
            @PathVariable("denimId") long denimId
    ) {
        cartService.pushCartItem(new CartPushRequest(token, denimId));
        return new ResponseEntity<>(OK);
    }

    @PatchMapping("/denims/{denimId}")
    public ResponseEntity<Void> updateCartItems(
            @CookieValue(value = "token", required = false) String token,
            @PathVariable("denimId") long denimId,
            @RequestParam(value = "isMinus") boolean isMinus
    ) {
        cartService.updateCartItems(new CartUpdateRequest(token, denimId, isMinus));
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartListDto>> getCartItems(
            @CookieValue(value = "token", required = false) String token) {
        return new ResponseEntity<>(cartService.getCartItems(token), OK);
    }
}