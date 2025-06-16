package com.foodapp.repo;

import com.foodapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {

   Restaurant findByrestaurantid(Long id);
    List<Restaurant>  findByRestaurantName(String restaurantName);

   List<Restaurant> findByCity(String location);

    List<Restaurant> findByRestaurantNameContainingIgnoreCase(String restaurantName);

    Optional<Restaurant> findByRestaurantNameAndOwnerEmail(String restaurantName, String ownerEmail);

    List<Restaurant> findByOwnerEmail(String email);
}
