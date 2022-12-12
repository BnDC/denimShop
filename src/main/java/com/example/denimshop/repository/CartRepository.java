package com.example.denimshop.repository;

import com.example.denimshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
//    @Query("delete from Cart c where c.cartId = :card_id")
//    void customDeleteById(long cart_id);
}
