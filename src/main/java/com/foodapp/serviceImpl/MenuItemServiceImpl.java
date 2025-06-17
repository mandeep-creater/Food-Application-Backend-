package com.foodapp.serviceImpl;


import com.foodapp.DTO.MenuItemCsvRepresentation;
import com.foodapp.DTO.MenuItemDTO;
import com.foodapp.DTO.MenuResponseDTO;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Restaurant;
import com.foodapp.exception.GlobalExceptionHandler;
import com.foodapp.helper.MenuItemMapper;
import com.foodapp.repo.MenuItemRepo;
import com.foodapp.repo.RestaurantRepo;
import com.foodapp.service.MenuItemService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.*;
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
    public Integer bulkCreateAndUpdate(MultipartFile file) {
        List<MenuItem> menuItems = parseCSV(file);
        menuItemRepo.saveAll(menuItems);  // Saves both new and updated entries
        return menuItems.size();
    }

    private List<MenuItem> parseCSV(MultipartFile file)  {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            HeaderColumnNameMappingStrategy<MenuItemCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(MenuItemCsvRepresentation.class);

            CsvToBean<MenuItemCsvRepresentation> csvToBean = new CsvToBeanBuilder<MenuItemCsvRepresentation>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<MenuItemCsvRepresentation> csvMenuItems = csvToBean.parse();

            Map<String, MenuItem> finalItemsToSave = new HashMap<>();

            for (MenuItemCsvRepresentation csv : csvMenuItems) {
                if (csv.getName() == null || csv.getRestaurantId() == null) {
                    // Skip or throw an exception for missing required fields
                    continue;
                }

                Restaurant restaurant = restaurantRepo.findById(csv.getRestaurantId())
                        .orElseThrow(() -> new RuntimeException("Restaurant not found with ID: " + csv.getRestaurantId()));

                // Try to find existing MenuItem by name + restaurant
                Optional<MenuItem> existingItemOpt = menuItemRepo.findByNameAndRestaurant(csv.getName(), restaurant);

                MenuItem menuItem;
                if (existingItemOpt.isPresent()) {
                    // Update existing item (e.g. quantity)
                    menuItem = existingItemOpt.get();
                    if (csv.getQuantity() != null && csv.getQuantity() > 0) {
                        menuItem.setQuantity(menuItem.getQuantity() + csv.getQuantity()); // Increment quantity
                    }
                    if (csv.getPrice() != null) menuItem.setPrice(csv.getPrice());
                    if (csv.getDescription() != null) menuItem.setDescription(csv.getDescription());
                    if (csv.getCategory() != null) menuItem.setCategory(csv.getCategory());
                    if (csv.getImage() != null) menuItem.setImage(csv.getImage());
                    if (csv.getAvailable() != null) menuItem.setAvailable(csv.getAvailable());
                } else {
                    // Create new item
                    menuItem = new MenuItem();
                    menuItem.setName(csv.getName());
                    menuItem.setDescription(csv.getDescription());
                    menuItem.setPrice(csv.getPrice());
                    menuItem.setQuantity(csv.getQuantity() != null ? csv.getQuantity() : 0);
                    menuItem.setCategory(csv.getCategory());
                    menuItem.setRestaurant(restaurant);
                    menuItem.setImage(csv.getImage());
                    menuItem.setAvailable(csv.getAvailable());


                }

                finalItemsToSave.put(csv.getName() + "_" + csv.getRestaurantId(), menuItem);
            }

            return new ArrayList<>(finalItemsToSave.values());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



        @Override
    public MenuResponseDTO getMenuItemById(Long id)  {
        if(!menuItemRepo.existsById(id)){
             GlobalExceptionHandler.menu_ItemNotFound(MenuItem.class,id);
        }
        MenuItem menuItem = menuItemRepo.findBymenuItemId(id);

        return new MenuResponseDTO(menuItem);

    }




    @Override
    public void deleteMenuItem(Long id) {
        if(menuItemRepo.existsById(id) ){
            menuItemRepo.deleteById(id);
        }
        else{
             GlobalExceptionHandler.menu_ItemNotFound(MenuItem.class,id);
        }

    }

    @Override
    public List<MenuResponseDTO> getAllMenuItems()  {
        List<MenuItem> menuItems = menuItemRepo.findAll();
        if (menuItems.isEmpty()) {
             GlobalExceptionHandler.menu_itemsNotFound(MenuItem.class);
        }
        return  menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getMenuItemsByCategory(String category)  {
        List<MenuItem> menuItems;
        if (category == null || category.trim().isEmpty()) {
            // Fallback: return all available items
            menuItems = menuItemRepo.findByAvailable(true); // or findAll()
        }
        else {
            menuItems = menuItemRepo.findByCategoryContainingIgnoreCase(category);
        }
        if (menuItems.isEmpty()) {
             GlobalExceptionHandler.categoryItemNotFound(MenuItem.class,category);
        }
        return menuItems.stream().map(MenuItemMapper::toResponseMenuItemDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDTO> getMenuItemsByPriceRange(Double minPrice, Double maxPrice) {

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

                 GlobalExceptionHandler.itemNotFoundWithPrice(MenuItem.class, minPrice, maxPrice);

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
