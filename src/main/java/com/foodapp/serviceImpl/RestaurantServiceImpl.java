package com.foodapp.serviceImpl;

import com.foodapp.DTO.RestaurantDTO;
import com.foodapp.entity.Order;
import com.foodapp.entity.Restaurant;
import com.foodapp.entity.User;
import com.foodapp.exception.GlobalExceptionHandler;
import com.foodapp.helper.RestaurantMapper;
import com.foodapp.repo.OrderRepo;
import com.foodapp.repo.RestaurantRepo;
import com.foodapp.repo.UserRepository;
import com.foodapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.foodapp.helper.RestaurantMapper.toEntity;
import static com.foodapp.helper.RestaurantMapper.toRestaurantDTO;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO,String OwnerEmail){

        User owner = userRepo.findByEmail(OwnerEmail)
                .orElseThrow(() -> GlobalExceptionHandler.notFound(User.class, OwnerEmail));

        Optional<Restaurant> existingRestaurant = restaurantRepo.findByRestaurantNameAndOwnerEmail(
                restaurantDTO.getRestaurantName(), restaurantDTO.getOwnerEmail());

        if (existingRestaurant.isPresent())
    {
        GlobalExceptionHandler.throwRestaurntExists(restaurantDTO.getId());
    }
        final Restaurant restaurant = toEntity(restaurantDTO, owner);

        final Restaurant savedrestaurant = restaurantRepo.save(restaurant);

        return toRestaurantDTO(savedrestaurant);
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream().map(RestaurantMapper::toRestaurantDTO).collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO getRestaurantById(Long id) {

        if(!restaurantRepo.existsById(id)){
            throw GlobalExceptionHandler.restaurantNotFound(Restaurant.class,id);
        }
        Restaurant restaurant = restaurantRepo.findByrestaurantid(id);

        return toRestaurantDTO(restaurant);
    }

    @Override
    public List<RestaurantDTO> getRestaurantsByLocation(String city) {

        if(city == null || city.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City cannot be empty");
        }

        if (city.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No restaurants found at : " + city
            );
        }
        List<Restaurant> restaurant = restaurantRepo.findByCity(city);
        return restaurant.stream().map(RestaurantMapper::toRestaurantDTO).collect(Collectors.toList());

    }

    @Override
    public List<RestaurantDTO> getRestaurantByName(String restaurantName) {
        if(restaurantName == null || restaurantName.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant name cannot be empty");
        }

        List<Restaurant> restaurants = restaurantRepo.findByRestaurantNameContainingIgnoreCase(restaurantName.trim());

        if (restaurants.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No restaurants found containing: " + restaurantName
            );
        }

        return restaurants.stream().map(RestaurantMapper::toRestaurantDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteRestaurant(Long id) {
            if(!restaurantRepo.existsById(id)){
                throw GlobalExceptionHandler.restaurantNotFound(Restaurant.class,id);
            }
            restaurantRepo.deleteById(id);
    }

    @Override
    public List<RestaurantDTO> getByOwnerEmail(String ownerEmail) {
        User owner = userRepo.findByEmail(ownerEmail)
               .orElseThrow(() -> GlobalExceptionHandler.notFound(User.class, ownerEmail));

        List<Restaurant> restaurants = restaurantRepo.findByOwnerEmail(ownerEmail);

        return restaurants.stream().map(RestaurantMapper::toRestaurantDTO).collect(Collectors.toList());
    }

    @Override
    public List<Order> GetOrdersByRestaurantId(Long restaurantid) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(restaurantid);

        // Fetch orders
        return orderRepo.findByRestaurant_Restaurantid( restaurantid);
    }


}
