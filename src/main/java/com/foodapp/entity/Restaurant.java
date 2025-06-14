package com.foodapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantid;

    @Column(name = "restaurant_name")
    private String restaurantName;

    private String location;

    private String image;

    private String Phone;

    private  String address;

    // Many restaurants can be owned by one user
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // Getters and Setters


    public Long getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Long restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Restaurant(Long restaurantid, String restaurantName, String location, String image, String phone, String address, User owner) {
        this.restaurantid = restaurantid;
        this.restaurantName = restaurantName;
        this.location = location;
        this.image = image;
      this.Phone = phone;
        this.address = address;
        this.owner = owner;
    }

    public Restaurant() {
    }
}
