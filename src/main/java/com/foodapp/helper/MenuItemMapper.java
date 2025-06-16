package com.foodapp.helper;

import com.foodapp.DTO.MenuItemDTO;
import com.foodapp.DTO.MenuResponseDTO;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Restaurant;

public class MenuItemMapper {

    // Convert Request DTO to Entity

    public static MenuItem toEntity(MenuItemDTO requestDTO, Restaurant restaurant) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(requestDTO.getName());
        menuItem.setPrice(requestDTO.getPrice());
        menuItem.setImage(requestDTO.getImage());
        menuItem.setCategory(requestDTO.getCategory());
        menuItem.setQuantity(requestDTO.getQuantity());
        menuItem.setDescription(requestDTO.getDescription());
        menuItem.setAvailable(requestDTO.getAvailable());
     menuItem.setRestaurant(restaurant); // Injected from controller after verifying ownership
        return menuItem;
    }

    // Convert Entity to Response DTO

    public static MenuItemDTO toMenuItemDto(MenuItem menuItem) {
        MenuItemDTO responseDTO = new MenuItemDTO();
        responseDTO.setMenuItemId(menuItem.getMenuItemId());
        responseDTO.setName(menuItem.getName());
        responseDTO.setPrice(menuItem.getPrice());
        responseDTO.setImage(menuItem.getImage());
        responseDTO.setCategory(menuItem.getCategory());
        responseDTO.setQuantity(menuItem.getQuantity());
        responseDTO.setDescription(menuItem.getDescription());
        responseDTO.setAvailable(menuItem.getAvailable());
       responseDTO.setRestaurantid(menuItem.getRestaurant().getRestaurantid()); // Send only the ID
        responseDTO.setRestaurantName(menuItem.getRestaurant().getRestaurantName());       // Optional: send name
        return responseDTO;
    }

    public static MenuResponseDTO toResponseMenuItemDto(MenuItem menuItem) {
        MenuResponseDTO dto = new MenuResponseDTO();
      //  dto.setMenuItemId(menuItem.getMenuItemId());
        dto.setName(menuItem.getName());
        dto.setPrice(menuItem.getPrice());
        dto.setImage(menuItem.getImage());
        dto.setCategory(menuItem.getCategory());
      //  dto.setQuantity(menuItem.getQuantity());
        dto.setDescription(menuItem.getDescription());
        dto.setAvailable(menuItem.getAvailable());
     //   dto.setRestaurantId(menuItem.getRestaurant().getRestaurantid()); // assuming restaurant is not null
        return dto;
    }

}
