package com.foodapp.DTO;

public class RestaurantDTO {
    private Long id;
    private String restaurantName;

    private String restaurantConatctNumber;
    private String city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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


    public RestaurantDTO(Long id, String restaurantName, String restaurantConatctNumber, String city, String image, String address, String ownerEmail) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantConatctNumber = restaurantConatctNumber;
        this.city = city;
        this.image = image;
        this.address = address;
        this.ownerEmail = ownerEmail;
    }

    public RestaurantDTO() {
    }
}
