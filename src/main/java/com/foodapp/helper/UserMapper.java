package com.foodapp.helper;

import com.foodapp.DTO.RestaurantDTO;
import com.foodapp.Response.UserRegistrationRequest;
import com.foodapp.Response.UserRegistrationResponse;
import com.foodapp.entity.Restaurant;
import com.foodapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    public static User toEntity(UserRegistrationRequest dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setGender(dto.getGender());
        user.setAddress(dto.getAddress());
        return user;
    }

    public static UserRegistrationResponse toResponse(User user) {
        UserRegistrationResponse response = new UserRegistrationResponse();


        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setGender(user.getGender());
        response.setAddress(user.getAddress());
        return response;
    }

}
