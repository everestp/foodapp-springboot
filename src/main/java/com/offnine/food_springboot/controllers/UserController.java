package com.offnine.food_springboot.controllers;


import com.offnine.food_springboot.request.UserRequest;
import com.offnine.food_springboot.response.UserResponse;
import com.offnine.food_springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;



import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
private  final UserService userService;

    @PostMapping("/register")

    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return  userService.registerUser(userRequest);


    }


}
