package com.foodapp.service;

import com.foodapp.DTO.CartItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartItemService {

    CartItemDTO addCartItem(Long menuItemId, int quantity, String email);

    // Get all cart items for a specific user
    List<CartItemDTO> getCartByUser(String email);

    // Remove a specific cart item
    void removeCartItem(Long cartItemId , boolean forceDelete);

    // Clear the entire cart for a specific user
    void emptyCart(String email);

    CartItemDTO getCartItemById(Long cartItemId) ;

    // Get total price of all items in the cart for a user
    double calculateCartTotal(String email);

    // Get total quantity of all items in the cart for a user
    int calculateCartQuantity(String email);

    // Get the last added item to the cart for a user
    CartItemDTO getLastAddedItem(String email) ;

    // Get the first added item to the cart for a user
    CartItemDTO getFirstAddedItem(String email) ;

    // Get the number of items in the cart for a user
    int getCartItemCount(String email);

    // Get the average price of all items in the cart for a user
    double getAveragePrice(String email);

    // Get the maximum quantity of any item in the cart for a user
    int getMaxQuantity(String email);

    // Get the minimum quantity of any item in the cart for a user
    int getMinQuantity(String email);

    // Get the total price of items in the cart for a user within a given price range
    double getTotalPriceInRange(String email, double minPrice, double maxPrice);

    // Get the average price of items in the cart for a user within a given price range
    double getAveragePriceInRange(String email, double minPrice, double maxPrice);

    // Get the maximum quantity of any item in the cart for a user within a given quantity range
    int getMaxQuantityInRange(String email, int minQuantity, int maxQuantity);

    // Get the minimum quantity of any item in the cart for a user within a given quantity range
    int getMinQuantityInRange(String email, int minQuantity, int maxQuantity);

    // Get the total price of items in the cart for a user within a given quantity range
    double getTotalPriceInQuantityRange(String email, int minQuantity, int maxQuantity);

    // Get the average price of items in the cart for a user within a given quantity range
    double getAveragePriceInQuantityRange(String email, int minQuantity, int maxQuantity);

    // Get the maximum price of any item in the cart for a user
    double getMaxPrice(String email);

    // Get the minimum price of any item in the cart for a user
    double getMinPrice(String email);

    // Get the total price of items in the cart for a user within a given price and quantity range
    double getTotalPriceInRangeAndQuantityRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity);
    // Get the average price of items in the cart for a user within a given price and quantity range
    double getAveragePriceInRangeAndQuantityRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity);

    // Get the maximum price of any item in the cart for a user within a given price and quantity range
    double getMaxPriceInRangeAndQuantityRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity);

    // Get the minimum price of any item in the cart for a user within a given price and quantity range
    double getMinPriceInRangeAndQuantityRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity);
    // Get the total price of items in the cart for a user within a given price, quantity, and category range
    double getTotalPriceInRangeAndQuantityRangeAndCategoryRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String category);

    // Get the average price of items in the cart for a user within a given price, quantity, and category range
    double getAveragePriceInRangeAndQuantityRangeAndCategoryRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String category);

    // Get the maximum price of any item in the cart for a user within a given price, quantity, and category range
    double getMaxPriceInRangeAndQuantityRangeAndCategoryRange(String email, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String category);


}






