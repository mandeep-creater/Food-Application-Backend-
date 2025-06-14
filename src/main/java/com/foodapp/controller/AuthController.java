package com.foodapp.controller;

import com.foodapp.Response.ApiResponse;
import com.foodapp.Response.AuthenticationResponse;
import com.foodapp.Response.UserRegistrationRequest;
import com.foodapp.Response.UserRegistrationResponse;
import com.foodapp.entity.Role;
import com.foodapp.entity.User;
import com.foodapp.serviceImpl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationServiceImpl authService;



//    @PostMapping("/register")
//    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest request){
//        UserRegistrationResponse res = authService.register(request);
//        return  ResponseEntity.status(HttpStatus.CREATED).body(res);
//    }
//

    @PostMapping("/register/user")
    public ResponseEntity<ApiResponse<UserRegistrationResponse>> registerUser(@RequestBody UserRegistrationRequest request){

        UserRegistrationResponse res = authService.register(request, Role.USER);

      ApiResponse<UserRegistrationResponse> result = new ApiResponse<>(HttpStatus.CREATED.value(), true,res);
      return  ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/register/deliveryboy")
    public ResponseEntity<ApiResponse<UserRegistrationResponse>> registerDeliveryBoy(@RequestBody UserRegistrationRequest request){

        UserRegistrationResponse res = authService.register(request, Role.DELIVERY_Boy);

        ApiResponse<UserRegistrationResponse> result = new ApiResponse<>(HttpStatus.CREATED.value(), true,res);
        return  ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<ApiResponse<UserRegistrationResponse>> registerRESTAURANT(@RequestBody UserRegistrationRequest request){

        UserRegistrationResponse res = authService.register(request, Role.RESTAURANT);

        ApiResponse<UserRegistrationResponse> result = new ApiResponse<>(HttpStatus.CREATED.value(), true,res);
        return  ResponseEntity.status(HttpStatus.CREATED).body(result);
    }



    //
//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
//        return ResponseEntity.ok(authService.authenticate(request));
//    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody User request) {
        AuthenticationResponse res =authService.authenticate(request);
        ApiResponse<AuthenticationResponse> result =
                new ApiResponse<>(HttpStatus.OK.value(), true, res);
        return ResponseEntity.ok(result);
    }
}
