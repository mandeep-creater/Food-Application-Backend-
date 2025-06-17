package com.foodapp.helper;

import com.foodapp.DTO.CartItemDTO;
import com.foodapp.entity.CartItem;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.User;

import java.time.LocalDateTime;

public class CartItemMapper {

    // Convert DTO to Entity
    public  static CartItem toEntity(CartItemDTO cartItemDto , User user, MenuItem menuItem) {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemDto.getCartItemId());
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setAddedAt(cartItemDto.getAddedAt() != null ? cartItemDto.getAddedAt() : LocalDateTime.now());
        cartItem.setMenuItem(menuItem);
        cartItem.setUser(user);
        return cartItem;

    }

    // Convert  Entity to DTO

    public  static CartItemDTO toDTO(CartItem cartItem) {
        CartItemDTO cartItemDto = new CartItemDTO();
        cartItemDto.setCartItemId(cartItem.getCartItemId());
        cartItemDto.setUserId(cartItem.getUser().getId());
        cartItemDto.setMenuItemId(cartItem.getMenuItem().getMenuItemId());
        cartItemDto.setMenuItemName(cartItem.getMenuItem().getName());
        cartItemDto.setMenuItemPrice(cartItem.getMenuItem().getPrice());
        cartItemDto.setMenuItemImage(cartItem.getMenuItem().getImage());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setAddedAt(cartItem.getAddedAt());
        return cartItemDto;
    }

//    public  static CartItemDTO CartItemResponse(CartItem entity){
//        CartItemDTO dto = new CartItemDTO();
//
//        dto.setCartItemId(entity.getCartItemId());
//        dto.setUserId(entity.getUser().getId());
//        dto.setMenuItemId(entity.getMenuItem().getMenuItemId());
//        dto.setMenuItemName(entity.getMenuItem().getName());
//        dto.setMenuItemImage(entity.getMenuItem().getImage());
//        dto.setMenuItemPrice(entity.getMenuItem().getPrice());
//        dto.setQuantity(entity.getQuantity());
//        dto.setAddedAt(entity.getAddedAt());
//
//        return dto;
//    }
}
