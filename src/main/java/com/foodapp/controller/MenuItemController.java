package com.foodapp.controller;

import com.foodapp.DTO.MenuItemDTO;
import com.foodapp.DTO.MenuResponseDTO;
import com.foodapp.DTO.RestaurantDTO;
import com.foodapp.Response.ApiResponse;
import com.foodapp.entity.Restaurant;
import com.foodapp.entity.User;
import com.foodapp.exception.GlobalExceptionHandler;
import com.foodapp.service.MenuItemService;
import com.foodapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant/menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;


    @PreAuthorize("RESTAURANT")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<MenuItemDTO>>createandUpdateMenuItem(@RequestBody MenuItemDTO menuItemDTO , Principal principal){

        String email = principal.getName();
        MenuItemDTO res = menuItemService.createOrUpdateMenuItem(menuItemDTO, email);
        ApiResponse<MenuItemDTO> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //@ApiParam(value = "CSV File", required = true) //when use swagger
//    @PreAuthorize("hasRole('RESTAURANT')")
    @PreAuthorize("RESTAURANT")
    @PostMapping(value="/bulk/upload" , consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<String>> bulkCreateAndUpdate(@RequestParam("file") MultipartFile file)  {
       Integer data = menuItemService.bulkCreateAndUpdate(file);
       String msg = data + " items uploaded successfully";
    ApiResponse<String> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, msg);
    return  new ResponseEntity<>(apiResponse,HttpStatus.OK)  ;
    }

    @GetMapping("/by-item-id/{id}")
    public ResponseEntity<ApiResponse<MenuResponseDTO>>getMenuItemById(@PathVariable("id") Long id)  {
        MenuResponseDTO res = menuItemService.getMenuItemById(id);
        ApiResponse<MenuResponseDTO> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PreAuthorize("RESTAURANT")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMenuItem(@PathVariable("id") Long id)  {

            menuItemService.deleteMenuItem(id);

        ApiResponse<String> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, "Item deleted successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getAllMenuItems()  {
        List<MenuResponseDTO> res = menuItemService.getAllMenuItems();
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getMenuItemsByCategory(@PathVariable("category") String category)  {
            List<MenuResponseDTO> res = menuItemService.getMenuItemsByCategory(category);
            ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/price-range/{minPrice}/{maxPrice}")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getMenuItemsByPriceRange(@PathVariable("minPrice") double minPrice, @PathVariable("maxPrice") double maxPrice) {
        List<MenuResponseDTO> res = menuItemService.getMenuItemsByPriceRange(minPrice, maxPrice);
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

   // @GetMapping("/rating/{minRating}/{maxRating}")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getMenuItemsByRating(@PathVariable("minRating") int minRating, @PathVariable("maxRating") int maxRating){
        List<MenuResponseDTO> res = menuItemService.getMenuItemsByRating(minRating, maxRating);
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping ("/isAvailable")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>>getMenuItemsByAvailability(){
        List<MenuResponseDTO> res = menuItemService.getMenuItemsByAvailability();
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //@GetMapping("/discount/{minDiscount}/{maxDiscount}")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getMenuItemsByDiscount(@PathVariable("minDiscount") double minDiscount, @PathVariable("maxDiscount") double maxDiscount){
        List<MenuResponseDTO> res = menuItemService.getMenuItemsByDiscount(minDiscount, maxDiscount);
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{restaurantName}")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getItemsByRestaurant(@PathVariable("restaurantName") String restaurantName){
        List<MenuResponseDTO> res = menuItemService.getItemsByRestaurant(restaurantName);
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

   // @GetMapping("/featured")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getFeaturedItems(){
        List<MenuResponseDTO> res = menuItemService.getFeaturedItems();
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> searchMenuItemsByName(@PathVariable("keyword") String keyword){
        List<MenuResponseDTO> res = menuItemService.searchMenuItemsByName(keyword);
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/sort/price-asc")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getItemsSortedByPriceAsc(){
        List<MenuResponseDTO> res = menuItemService.getItemsSortedByPriceAsc();
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/sort/price-desc")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getItemsSortedByPriceDesc(){
        List<MenuResponseDTO> res = menuItemService.getItemsSortedByPriceDesc();
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

  //  @GetMapping("/sort/rating-desc")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getItemsSortedByRatingDesc(){
        List<MenuResponseDTO> res = menuItemService.getItemsSortedByRatingDesc();
        ApiResponse<List<MenuResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
