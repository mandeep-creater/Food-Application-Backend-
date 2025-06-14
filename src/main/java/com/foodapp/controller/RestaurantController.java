package com.foodapp.controller;

import com.foodapp.DTO.RestaurantDTO;
import com.foodapp.Response.ApiResponse;
import com.foodapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PreAuthorize("RESTAURANT")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RestaurantDTO>>addRestaurant(Principal principal , @RequestBody RestaurantDTO restaurantDTO){

        String email= principal.getName();
        //restaurantDTO.setOwnerEmail(email);
        RestaurantDTO res= restaurantService.addRestaurant(restaurantDTO,email);

        ApiResponse<RestaurantDTO> result = new ApiResponse<>(HttpStatus.CREATED.value(), true,res);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getAllRestaurant(){

      List<RestaurantDTO> res =restaurantService.getAllRestaurants();
      ApiResponse<List<RestaurantDTO>> result = new ApiResponse<>(HttpStatus.OK.value(), true,res);
      return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ApiResponse<RestaurantDTO>> getRestaurantById(@PathVariable("Id") Long Id){

        RestaurantDTO res =restaurantService.getRestaurantById(Id);
        ApiResponse<RestaurantDTO> result = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
