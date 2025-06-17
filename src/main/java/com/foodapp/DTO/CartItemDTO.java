package com.foodapp.DTO;

import java.time.LocalDateTime;

public class CartItemDTO {

    private Long cartItemId;
    private Long userId;
    private Long menuItemId;
    private String menuItemName;
    private String menuItemImage;
    private Double menuItemPrice;
    private int quantity;
    private LocalDateTime addedAt;

    // Constructors
    public CartItemDTO() {}

    public CartItemDTO(Long cartItemId, Long userId, Long menuItemId, String menuItemName,
                       String menuItemImage, Double menuItemPrice, int quantity, LocalDateTime addedAt) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.menuItemImage = menuItemImage;
        this.menuItemPrice = menuItemPrice;
        this.quantity = quantity;
        this.addedAt = addedAt;
    }

    // Getters and Setters

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public void setMenuItemPrice(Double menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public String getMenuItemImage() {
        return menuItemImage;
    }

    public void setMenuItemImage(String menuItemImage) {
        this.menuItemImage = menuItemImage;
    }

    public Double getMenuItemPrice() {
        return menuItemPrice;
    }
}