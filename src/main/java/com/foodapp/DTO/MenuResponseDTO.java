package com.foodapp.DTO;

import com.foodapp.entity.MenuItem;

public class MenuResponseDTO {

    private String name;
    private Double price;
    private String image;
    private String description;
    private Boolean available;
    private String category;
    private String restaurantName;

    // ✅ Constructor that maps from MenuItem
    public MenuResponseDTO(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
        this.image = menuItem.getImage();
        this.description = menuItem.getDescription();
        this.available = menuItem.getAvailable();
        this.category = menuItem.getCategory();
        if (menuItem.getRestaurant() != null) {
            this.restaurantName = menuItem.getRestaurant().getRestaurantName();
        }
    }

    // ✅ Default no-args constructor
    public MenuResponseDTO() {}

    // ✅ Getters and Setters
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
