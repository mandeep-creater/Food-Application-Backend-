package com.foodapp.DTO;

public class MenuItemDTO {

    private Long menuItemId;


    private String name;
    private Double price;
    private String image;
    private String description;
    private Boolean available;
    private String category;
    private Long restaurantid;
    private String restaurantName;


    private  int quantity; // Added for the cart functionality

    // Constructors
    public MenuItemDTO() {}

    public MenuItemDTO(Long menuItemId, String name, Double price, String image, String description,
                       Boolean available, String category, int quantity, Long restaurantid, String restaurantName) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.quantity=quantity;
        this.available = available;
        this.category = category;
        this.restaurantid = restaurantid;
        this.restaurantName = restaurantName;
    }

    // Getters & Setters

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

    public Long getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Long restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
