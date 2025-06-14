package com.foodapp.repo;

import com.foodapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {

   Restaurant findByrestaurantid(Long id);
    Optional<Restaurant> findByRestaurantName(String  restaurantName);

    Optional<Restaurant> findByLocation(String location);

    Optional<Restaurant> findByRestaurantNameAndOwnerEmail(String restaurantName, String ownerEmail);
}
