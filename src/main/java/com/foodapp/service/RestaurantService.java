package com.foodapp.service;

import com.foodapp.DTO.RestaurantDTO;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO,String OwnerEmail);

    List<RestaurantDTO> getAllRestaurants();

    RestaurantDTO getRestaurantById(Long id);

    List<RestaurantDTO> getRestaurantsByLocation(String location);

    RestaurantDTO getRestaurantByName(String name);

    void deleteRestaurant(Long id);
}
