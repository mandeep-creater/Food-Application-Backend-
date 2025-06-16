package com.foodapp.entity;

import com.foodapp.DTO.MenuResponseDTO;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "menu_items")
public class MenuItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuItemId;


    private String name;

    private Double price;

    private String image;


    private int quantity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String description;      // optional
    private Boolean available;       // e.g., out-of-stock flag
    private String category;         // e.g., Biryani, Drinks, Pizza


    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public MenuItem(Long menuItemId, String name, Double price, String image, Restaurant restaurant,int quantity, String description, Boolean available, String category) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.restaurant = restaurant;
        this.quantity = quantity;
        this.description = description;
        this.available = available;
        this.category = category;
    }

    public MenuItem() {}


}
