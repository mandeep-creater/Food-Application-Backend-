package com.foodapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    private int quantity;


    private LocalDateTime addedAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_preview_id")
//    private OrderPreview orderPreview;


    // Getters and setters...


    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
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

//    public OrderPreview getOrderPreview() {
//        return orderPreview;
//    }
//
//    public void setOrderPreview(OrderPreview orderPreview) {
//        this.orderPreview = orderPreview;
//    }



    public CartItem(User user, MenuItem menuItem, int quantity, LocalDateTime addedAt) {
        this.user = user;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.addedAt = addedAt;
    }
    public CartItem() {
    }

}
