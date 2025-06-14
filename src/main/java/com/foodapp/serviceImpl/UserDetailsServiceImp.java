package com.foodapp.serviceImpl;

import com.foodapp.Response.AuthenticationResponse;
import com.foodapp.Response.UserRegistrationResponse;
import com.foodapp.entity.User;
import com.foodapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not found"));
    }

    public UserRegistrationResponse getUserProfile(String email) {

       User user= userRepository.findByEmail(email) .orElseThrow(()-> new RuntimeException("usernot found with email "+email));

       return new  UserRegistrationResponse(user.getName(), user.getEmail(),
                                            user.getAddress(), user.getGender(), user.getPhoneNumber());
    }
}
