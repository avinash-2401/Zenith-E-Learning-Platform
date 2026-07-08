package com.avinash.book_network.service;

import com.avinash.book_network.config.SecurityConfig;
import com.avinash.book_network.dto.AuthenticationResponse;
import com.avinash.book_network.dto.LoginRequest;
import com.avinash.book_network.dto.RegisterRequest;
import com.avinash.book_network.entity.User;
import com.avinash.book_network.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("user already exit!");

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        return userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

//    public String login(LoginRequest loginRequest){
//
//          User user = userRepository.findByEmail(loginRequest.getEmail())
//              .orElseThrow(() -> new RuntimeException("User not found"));
//
//          if(!passwordEncoder.matches(
//                  loginRequest.getPassword() ,
//                  user.getPassword())){
//              throw new RuntimeException("Invalid password");
//          }
//          return "Login Successful";
//
//    }
}
