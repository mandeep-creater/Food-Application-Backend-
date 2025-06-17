package com.foodapp.DTO;

import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Restaurant;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Builder
public class MenuItemCsvRepresentation {
    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "price")
    private Double price;
    @CsvBindByName(column = "image")
    private String image;

    @CsvBindByName(column = "quantity")
    private Integer quantity;

    @CsvBindByName(column = "restaurant_id")
    private Long restaurantId;

    @CsvBindByName(column = "description")
    private String description;      // optional

    @CsvBindByName(column = "available")
    private Boolean available;       // e.g., out-of-stock flag

    @CsvBindByName(column = "category")
    private String category;         // e.g., Biryani, Drinks, Pizza


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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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

//    public MenuItemCsvRepresentation(MenuItem menuItem) {
//        this.name = name;
//    }
}
