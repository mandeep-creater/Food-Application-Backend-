package com.foodapp.DTO;

import com.foodapp.entity.User;

public class RestaurantDTO {
    private Long id;
    private String restaurantName;

    private String restaurantConatctNumber;
    private String location;
    private String image;
    private  String address;
    private String ownerEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantConatctNumber() {
        return restaurantConatctNumber;
    }

    public void setRestaurantConatctNumber(String restaurantConatctNumber) {
        this.restaurantConatctNumber = restaurantConatctNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }


    public RestaurantDTO(Long id, String restaurantName, String restaurantConatctNumber, String location, String image, String address, String ownerEmail) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantConatctNumber = restaurantConatctNumber;
        this.location = location;
        this.image = image;
        this.address = address;
        this.ownerEmail = ownerEmail;
    }

    public RestaurantDTO() {
    }
}
