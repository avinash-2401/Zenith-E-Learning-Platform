package com.avinash.book_network.controller;

import com.avinash.book_network.dto.AuthenticationResponse;
import com.avinash.book_network.dto.LoginRequest;
import com.avinash.book_network.dto.RegisterRequest;
import com.avinash.book_network.entity.User;
import com.avinash.book_network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request){

        User user = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest));
    }




}
