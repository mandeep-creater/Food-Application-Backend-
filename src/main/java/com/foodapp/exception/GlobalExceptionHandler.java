package com.foodapp.exception;

import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Restaurant;
import com.foodapp.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static ResponseStatusException userNotExists(Class<User> userClass, String email) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "user NOT FOUND. User: " + email + " does not exist."
        );
    }

    public static ResponseStatusException menuItemNotExists(Long menuItemId) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Item NOT FOUND in MENU : " + menuItemId
        );
    }

    public static void cartItemNotExists(Long cartItemId) {
        throw  new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Item NOT FOUND in Cart : " + cartItemId
        );
    }

    public static void cartItemEmpty(String email) {
        throw  new ResponseStatusException(
                HttpStatus.OK,
                "Cart is Empty : " + email
        );
    }

    public static void NoOrdersFoundForUser(String userEmail) {
        throw  new ResponseStatusException(
                HttpStatus.OK,
                "No Order Present : " + userEmail
        );
    }

    public static void NoOrdersFound() {

        throw  new ResponseStatusException(
                HttpStatus.OK,
                "No Order Present  "
        );
    }

    public static void NoLiveOrdersForUser(String userEmail) {
        throw  new ResponseStatusException(
                HttpStatus.OK,
                "No Live Update Present  "
        );
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", "The requested URL was not found on the server.");
        errorResponse.put("path", ex.getRequestURL());
        errorResponse.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public static ResponseStatusException restaurantNotFound(Class<Restaurant> res , Long id) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                " Restaurant not found with ID: " + id
        );
    }

    public static ResponseStatusException unauthorizedException(String email, String email1) {
        return new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Unauthorized access. User: " + email + " is not authorized to access restaurant: " + email1
        );
    }

    public static void menu_ItemNotFound(Class<MenuItem> menuItemClass, Long id) {
        throw  new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                " Item not found with ID: " + id
        );
    }

    public static ResponseStatusException menu_itemsNotFound(Class<MenuItem> menuItemClass) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                " Item not found May be it is Empty or not available"
        );
    }

    public static Exception itemNotFoundWithPrice(Class<MenuItem> menuItemClass, Double minPrice, Double maxPrice) {

        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                " Item not found with minimum price: " + minPrice + " and maximum price: " + maxPrice
        );
    }

    public static Exception categoryItemNotFound(Class<MenuItem> menuItemClass, String category) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                " Item not found with category: " + category
        );
    }


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("status", ex.getStatusCode().value());
        response.put("message", ex.getReason());
        response.put("path", request.getRequestURI());
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    // (Optional) Handle other general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("status", 500);
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseStatusException throwUserExists(String email) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "User already exists with email: " + email
        );
    }

    public static ResponseStatusException throwNotFound(Class<?> entity, Object id) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                entity + " not found with ID: " + id
        );
    }

    public static <T> RuntimeException notFound(Class<T> clazz, Object id) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                clazz.getSimpleName() + " not found with ID: " + id
        );
    }


    public static ResponseStatusException throwRestaurntExists(Long id) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Restaurant already exists : " + id
        );
    }


}
