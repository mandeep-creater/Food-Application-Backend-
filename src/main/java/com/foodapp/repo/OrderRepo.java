package com.foodapp.repo;

import com.foodapp.entity.Order;
import com.foodapp.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findByEmailAndIsPreviewTrue(String email);

    void deleteByEmailAndIsPreviewTrue(String email);


    List<Order> findAllByCustomerEmail(String userEmail);

    List<Order> findAllByOrderStatus(Status status);

    List<Order> findAllByCustomerEmailAndOrderStatus(String userEmail, Status status);

    List<Order> findByRestaurant_Restaurantid(Long restaurantid);


}
