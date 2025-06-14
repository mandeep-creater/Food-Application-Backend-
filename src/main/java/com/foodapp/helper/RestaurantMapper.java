package com.foodapp.helper;

import com.foodapp.DTO.RestaurantDTO;
import com.foodapp.entity.Restaurant;
import com.foodapp.entity.User;

public class RestaurantMapper {


    public static Restaurant toEntity(RestaurantDTO restaurantDTO, User owner) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setImage(restaurantDTO.getImage());
        restaurant.setPhone(restaurantDTO.getRestaurantConatctNumber());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setOwner(owner);
        return restaurant;
    }

    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getRestaurantid());
        restaurantDTO.setRestaurantName(restaurant.getRestaurantName());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setLocation(restaurant.getLocation());
        restaurantDTO.setRestaurantConatctNumber(restaurant.getPhone());
        restaurantDTO.setImage(restaurant.getImage());

        // ✅ Only set email from User object
        if (restaurant.getOwner() != null) {
           // restaurantDTO.setOwnerEmail(restaurant.getOwner().getEmail());
            restaurantDTO.setOwnerEmail(restaurant.getOwner().getEmail());
        }

        return restaurantDTO;
    }
}
