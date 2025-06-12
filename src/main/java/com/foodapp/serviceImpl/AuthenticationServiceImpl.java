package com.foodapp.serviceImpl;

import com.foodapp.Response.AuthenticationResponse;
import com.foodapp.Response.UserRegistrationRequest;
import com.foodapp.Response.UserRegistrationResponse;
import com.foodapp.entity.Role;
import com.foodapp.entity.User;
import com.foodapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.foodapp.helper.UserMapper.toEntity;
import static com.foodapp.helper.UserMapper.toResponse;

@Service
public class AuthenticationServiceImpl {

    @Autowired
    private  UserRepository repository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;





    public UserRegistrationResponse register(UserRegistrationRequest request, Role role){

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            // Handle user already exists, e.g. throw an exception or return null or some error response
            throw new RuntimeException("User already exists");
        }
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setName(request.getName());
//        user.setAddress(request.getAddress());
//        user.setGender(request.getGender());
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER);

final User user = toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
final User savedUser =repository.save(user);
 return toResponse(savedUser);


    }


    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtService.generateAccessToken(user);

        return new AuthenticationResponse(accessToken, "User login was successful");
    }

}
