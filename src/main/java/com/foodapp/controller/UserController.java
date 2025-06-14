package com.foodapp.controller;

import com.foodapp.Response.ApiResponse;
import com.foodapp.Response.AuthenticationResponse;
import com.foodapp.Response.UserRegistrationResponse;
import com.foodapp.serviceImpl.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController{

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserRegistrationResponse>>getUserProfile(Principal principal ){

        String email = principal.getName();
        UserRegistrationResponse res = userDetailsServiceImp.getUserProfile(email);

        ApiResponse<UserRegistrationResponse> result = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return ResponseEntity.ok(result);
    }
}
