package com.foodapp.service;

import com.foodapp.DTO.MenuItemDTO;
import com.foodapp.DTO.MenuResponseDTO;
import com.foodapp.entity.MenuItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuItemService {

   public MenuItemDTO createOrUpdateMenuItem(MenuItemDTO menuItemDTO ,String email);

   public MenuResponseDTO getMenuItemById(Long id) ;

    Integer bulkCreateAndUpdate(MultipartFile file) ;


   public void deleteMenuItem(Long id) ;

   public List<MenuResponseDTO> getAllMenuItems() ;

   public  List<MenuResponseDTO> getMenuItemsByCategory(String category) ;

   public List<MenuResponseDTO> getMenuItemsByPriceRange(Double minPrice, Double maxPrice) ;

   public List<MenuResponseDTO> getMenuItemsByRating(int minRating, int maxRating);

   public List<MenuResponseDTO> getMenuItemsByAvailability();

   public List<MenuResponseDTO> getMenuItemsByDiscount(double minDiscount, double maxDiscount);

   public List<MenuResponseDTO> getItemsByRestaurant(String restaurantName);

    public List<MenuResponseDTO>  getFeaturedItems();

    public  List<MenuResponseDTO>  searchMenuItemsByName(String keyword);


    List<MenuResponseDTO> getItemsSortedByPriceAsc();
    List<MenuResponseDTO> getItemsSortedByPriceDesc();
    List<MenuResponseDTO> getItemsSortedByRatingDesc();



}
