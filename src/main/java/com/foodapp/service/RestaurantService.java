package com.foodapp.service;

import com.foodapp.DTO.RestaurantDTO;
import com.foodapp.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO,String OwnerEmail);

    List<RestaurantDTO> getAllRestaurants();

    RestaurantDTO getRestaurantById(Long id);

    List<RestaurantDTO> getRestaurantsByLocation(String location);

    List<RestaurantDTO> getRestaurantByName(String name);

    void deleteRestaurant(Long id);


    List<RestaurantDTO>getByOwnerEmail(String ownerEmail);
}
