package com.foodapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantid;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "city")
    private String city;

    private String image;

    private String Phone;

    private  String address;

    // Many restaurants can be owned by one user
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> MenuItems;

    // Getters and Setters


    public Long getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Long restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

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


    public List<MenuItem> getMenuItems() {
        return MenuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        MenuItems = menuItems;
    }

    public Restaurant(Long restaurantid, String restaurantName, String city, String image, String phone, String address, User owner) {
        this.restaurantid = restaurantid;
        this.restaurantName = restaurantName;
        this.city = city;
        this.image = image;
      this.Phone = phone;
        this.address = address;
        this.owner = owner;
    }


    public Restaurant() {
    }
}
