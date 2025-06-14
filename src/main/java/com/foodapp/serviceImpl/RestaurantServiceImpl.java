package com.foodapp.serviceImpl;

import com.foodapp.DTO.RestaurantDTO;
import com.foodapp.entity.Restaurant;
import com.foodapp.entity.User;
import com.foodapp.exception.GlobalExceptionHandler;
import com.foodapp.helper.RestaurantMapper;
import com.foodapp.repo.RestaurantRepo;
import com.foodapp.repo.UserRepository;
import com.foodapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<RestaurantDTO> getRestaurantsByLocation(String location) {
        return null;
    }

    @Override
    public RestaurantDTO getRestaurantByName(String name) {
        return null;
    }

    @Override
    public void deleteRestaurant(Long id) {

    }
}
