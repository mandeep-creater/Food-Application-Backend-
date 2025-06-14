package com.foodapp.exception;

import com.foodapp.entity.Restaurant;
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


    public static ResponseStatusException restaurantNotFound(Class<Restaurant> res , Long id) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                " Restaurant not found with ID: " + id
        );
    }


    // Handles 404 - Path Not Found
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("status", 404);
        response.put("message", "The requested path does not exist.");
        response.put("path", ex.getRequestURL());
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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

    public static void throwUserExists(String email) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "User already exists with email: " + email
        );
    }

    public static void throwNotFound(Class<?> entity, Object id) {
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


    public static void throwRestaurntExists(Long id) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Restaurant already exists : " + id
        );
    }


}
