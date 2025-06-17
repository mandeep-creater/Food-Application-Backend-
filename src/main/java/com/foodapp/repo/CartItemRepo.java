package com.foodapp.repo;

import com.foodapp.entity.CartItem;

import com.foodapp.entity.MenuItem;
import com.foodapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByUserAndMenuItem(User user, MenuItem menuItem);


    void delete(CartItem cartItem);

    List<CartItem> findAllByUser(User user);

    CartItem findFirstByUserOrderByAddedAtDesc(User user);

    CartItem findFirstByUserOrderByAddedAtAsc(User user);
}
