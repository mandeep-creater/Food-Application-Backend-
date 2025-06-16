package com.foodapp.serviceImpl;


import com.foodapp.DTO.MenuItemDTO;
import com.foodapp.DTO.MenuResponseDTO;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Restaurant;
import com.foodapp.exception.GlobalExceptionHandler;
import com.foodapp.helper.MenuItemMapper;
import com.foodapp.repo.MenuItemRepo;
import com.foodapp.repo.RestaurantRepo;
import com.foodapp.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.foodapp.helper.MenuItemMapper.toEntity;
import static com.foodapp.helper.MenuItemMapper.toMenuItemDto;



@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private MenuItemRepo menuItemRepo;

    @Override
    public MenuItemDTO createOrUpdateMenuItem(MenuItemDTO menuItemDTO,String email) {

        if(!restaurantRepo.existsById(menuItemDTO.getRestaurantid())){
            throw GlobalExceptionHandler.restaurantNotFound(Restaurant.class, menuItemDTO.getRestaurantid());
        }

        Restaurant restaurant = restaurantRepo.findByrestaurantid(menuItemDTO.getRestaurantid());

        if (!restaurant.getOwner().getEmail().equals(email)) {
            throw GlobalExceptionHandler.unauthorizedException(email, restaurant.getOwner().getEmail());
        }


      Optional<MenuItem>isItemPresent=  menuItemRepo.findByNameAndRestaurant_Restaurantid(menuItemDTO.getName(), restaurant.getRestaurantid());

        MenuItem menuItem = new MenuItem();

        if(isItemPresent.isEmpty()){
            menuItem = toEntity(menuItemDTO,restaurant);
        }else{
            menuItem=isItemPresent.get();
            menuItem.setQuantity(menuItem.getQuantity()+menuItemDTO.getQuantity());
        }

        return toMenuItemDto(menuItemRepo.save(menuItem));
    }



    @Override
    public MenuResponseDTO getMenuItemById(Long id) throws Exception {
        if(!menuItemRepo.existsById(id)){
            throw GlobalExceptionHandler.menu_ItemNotFound(MenuItem.class,id);
        }
        MenuItem menuItem = menuItemRepo.findBymenuItemId(id);

        return new MenuResponseDTO(menuItem);

    }


    @Override
    public void deleteMenuItem(Long id) throws Exception {
        if(menuItemRepo.existsById(id) ){
            menuItemRepo.deleteById(id);
        }
        else{
            throw GlobalExceptionHandler.menu_ItemNotFound(MenuItem.class,id);
        }

    }

    @Override
    public List<MenuResponseDTO> getAllMenuItems() throws Exception {
        List<MenuItem> menuItems = menuItemRepo.findAll();
        if (menuItems.isEmpty()) {
            throw GlobalExceptionHandler.menu_itemsNotFound(MenuItem.class);
        }
        return  menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getMenuItemsByCategory(String category) throws Exception {
        List<MenuItem> menuItems;
        if (category == null || category.trim().isEmpty()) {
            // Fallback: return all available items
            menuItems = menuItemRepo.findByAvailable(true); // or findAll()
        }
        else {
            menuItems = menuItemRepo.findByCategoryContainingIgnoreCase(category);
        }
        if (menuItems.isEmpty()) {
            throw GlobalExceptionHandler.categoryItemNotFound(MenuItem.class,category);
        }
        return menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getMenuItemsByPriceRange(Double minPrice, Double maxPrice) throws Exception {

        List<MenuItem> menuItems;
        if (minPrice == null && maxPrice == null) {
            return getMenuItemsByAvailability();
        }

        else if (minPrice != null && maxPrice == null) {
             menuItems = menuItemRepo.findByPriceGreaterThanEqual(minPrice);
        }

        else if (minPrice == null && maxPrice != null) {
              menuItems = menuItemRepo.findByPriceLessThanEqual(maxPrice);
        }
        else {
           menuItems = menuItemRepo.findByPriceBetween(minPrice, maxPrice);

        }
        if (menuItems.isEmpty()) {

                throw GlobalExceptionHandler.itemNotFoundWithPrice(MenuItem.class, minPrice, maxPrice);

        }
        return menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getMenuItemsByRating(int minRating, int maxRating) {
        return null;
    }

    @Override
    public List<MenuResponseDTO> getMenuItemsByAvailability() {
       List<MenuItem> menuItems = menuItemRepo.findByAvailable(true);
        if (menuItems.isEmpty()) {
            throw GlobalExceptionHandler.menu_itemsNotFound(MenuItem.class);
        }
       return menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getMenuItemsByDiscount(double minDiscount, double maxDiscount) {
        return null;
    }

    @Override
    public List<MenuResponseDTO> getItemsByRestaurant(String restaurantName) {
        if(restaurantName == null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant name cannot be empty");
        }
       List<MenuItem>menuItems = menuItemRepo.findByRestaurant_RestaurantNameContainingIgnoreCase(restaurantName);
        if (menuItems.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No Item found By : " + restaurantName
            );
        }
       return menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getFeaturedItems() {
        return null;
    }

    @Override
    public List<MenuResponseDTO> searchMenuItemsByName(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant name cannot be empty");
        }
        List<MenuItem> menuItems = menuItemRepo.findByRestaurant_RestaurantNameContainingIgnoreCase(keyword);
        if (menuItems.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No Item found By : " + keyword
            );
        }
        return menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getItemsSortedByPriceAsc() {
        List<MenuItem> menuItems = menuItemRepo.findAllByOrderByPriceAsc();
        if(menuItems.isEmpty()){
            throw GlobalExceptionHandler.menu_itemsNotFound(MenuItem.class);
        }
        return menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());

    }

    @Override
    public List<MenuResponseDTO> getItemsSortedByPriceDesc() {
       List<MenuItem> menuItems = menuItemRepo.findAllByOrderByPriceDesc();
        if(menuItems.isEmpty()){
            throw GlobalExceptionHandler.menu_itemsNotFound(MenuItem.class);
        }
       return menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getItemsSortedByRatingDesc() {
        return null;
    }
}
