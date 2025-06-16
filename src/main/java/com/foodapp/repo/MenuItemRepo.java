package com.foodapp.repo;

import com.foodapp.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByNameAndRestaurant_Restaurantid(String name, Long restaurantid);

    MenuItem findBymenuItemId(Long id);

    List<MenuItem> findByCategory(String category);

    List<MenuItem> findByPriceBetween(double minPrice, double maxPrice);

    List<MenuItem> findByAvailable(boolean available);

    List<MenuItem> findAllByOrderByPriceAsc();

    List<MenuItem> findAllByOrderByPriceDesc();

    List<MenuItem> findByPriceLessThanEqual(Double maxPrice);

    List<MenuItem> findByPriceGreaterThanEqual(Double minPrice);

    List<MenuItem> findByCategoryContainingIgnoreCase(String category);

    List<MenuItem> findByRestaurant_RestaurantNameContainingIgnoreCase(String restaurantName);
}
