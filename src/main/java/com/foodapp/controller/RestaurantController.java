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
    private ApiResponse<List<RestaurantDTO>> result;

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

    @GetMapping("/search/byCity/{city}")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getRestaurantByLocation(@PathVariable("city") String city){
        List<RestaurantDTO> res = restaurantService.getRestaurantsByLocation(city);
        ApiResponse<List<RestaurantDTO>> result = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/search/byName/{name}")
    public  ResponseEntity<ApiResponse<List<RestaurantDTO>>> getRestaurantByName(@PathVariable("name") String name){
         List<RestaurantDTO> res = restaurantService.getRestaurantByName(name);
        ApiResponse<List<RestaurantDTO>> result = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRestaurant(@PathVariable("id") Long id){
        restaurantService.deleteRestaurant(id);
        ApiResponse<String> result = new ApiResponse<>(HttpStatus.OK.value(), true, "Restaurant deleted successfully");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search/byOwnerEmail/{email}")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getByOwnerByOwnerEmail(@PathVariable("email") String email){
        List<RestaurantDTO> res = restaurantService.getByOwnerEmail(email);
        ApiResponse<List<RestaurantDTO>> result = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return ResponseEntity.ok(result);
    }
}
