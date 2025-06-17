package com.foodapp.controller;

import com.foodapp.DTO.AddToCartRequestDTO;
import com.foodapp.DTO.CartItemDTO;
import com.foodapp.Response.ApiResponse;
import com.foodapp.repo.CartItemRepo;
import com.foodapp.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant/menu/item/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
    @PreAuthorize("User")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartItemDTO>>addCartItem(Principal principal, @RequestBody AddToCartRequestDTO requestDTO) {
       String email= principal.getName();
        CartItemDTO addedCartItem = cartItemService.addCartItem(requestDTO.getMenuItemId(),requestDTO.getQuantity() , email);
        ApiResponse<CartItemDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), true,addedCartItem);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CartItemDTO>>> getCartByUser(Principal principal) {
            String email= principal.getName();
          List<CartItemDTO> cart = cartItemService.getCartByUser(email);
           ApiResponse< List<CartItemDTO>>response = new ApiResponse<>(HttpStatus.OK.value(), true,cart);
           return new ResponseEntity<>(response, HttpStatus.OK);
    }

        @DeleteMapping("reduce-cartItem/{itemId}") //for Single Quantity Item
    public ResponseEntity<ApiResponse<String>> reduceCartItem(@PathVariable Long itemId)  {
             cartItemService.removeCartItem(itemId ,  false);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true, "Item removed from cart successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("remove-cartItem/{itemId}")
    public ResponseEntity<ApiResponse<String>> removeCartItemAll(@PathVariable Long itemId)  {
          cartItemService.removeCartItem(itemId , true);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true, "Item removed from cart successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/empty")
    public ResponseEntity<ApiResponse<String>> emptyCart(Principal principal) {
        String email= principal.getName();
        cartItemService.emptyCart(email);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true, "Cart emptied successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cart-Item/{itemId}")
    public ResponseEntity<ApiResponse<CartItemDTO>> getCartItemById(@PathVariable Long itemId)  {
        CartItemDTO cartItem = cartItemService.getCartItemById(itemId);
        ApiResponse<CartItemDTO> response = new ApiResponse<>(HttpStatus.OK.value(), true, cartItem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/total")
    public ResponseEntity<ApiResponse<Double>> calculateCartTotal(Principal principal) {
        String email= principal.getName();
        double total = cartItemService.calculateCartTotal(email);
        ApiResponse<Double> response = new ApiResponse<>(HttpStatus.OK.value(), true, total);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/quantity")
    public ResponseEntity<ApiResponse<Integer>> calculateCartQuantity(Principal principal) {
        String email= principal.getName();
        int quantity = cartItemService.calculateCartQuantity(email);
        ApiResponse<Integer> response = new ApiResponse<>(HttpStatus.OK.value(), true, quantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/recently-added")
    public ResponseEntity<ApiResponse<CartItemDTO>> getLastAddedItem(Principal principal)  {
        String email= principal.getName();
        CartItemDTO lastAddedItem = cartItemService.getLastAddedItem(email);
        ApiResponse<CartItemDTO> response = new ApiResponse<>(HttpStatus.OK.value(), true, lastAddedItem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/first-added")
    public ResponseEntity<ApiResponse<CartItemDTO>> getFirstAddedItem(Principal principal)  {
        String email= principal.getName();
        CartItemDTO firstAddedItem = cartItemService.getFirstAddedItem(email);
        ApiResponse<CartItemDTO> response = new ApiResponse<>(HttpStatus.OK.value(), true, firstAddedItem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<String>>getCartItemCount(Principal principal)  {
        String email= principal.getName();
        int count = cartItemService.getCartItemCount(email);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true, "Cart Item Count: "+count);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/average-price")
    public ResponseEntity<ApiResponse<String>> getAveragePrice(Principal principal)  {
        String email= principal.getName();
        double averagePrice = cartItemService.getAveragePrice(email);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true, "Average Price: "+averagePrice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/max-quantity")
    public ResponseEntity<ApiResponse<String>> getMaxQuantity(Principal principal)  {
        String email= principal.getName();
        int maxQuantity = cartItemService.getMaxQuantity(email);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true, "Max Quantity: "+maxQuantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/min-quantity")
    public ResponseEntity<ApiResponse<String>> getMinQuantity(Principal principal)  {
        String email= principal.getName();
        int minQuantity = cartItemService.getMinQuantity(email);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true, "Min Quantity: "+minQuantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
