package com.offnine.food_springboot.services;

import com.offnine.food_springboot.request.UserRequest;
import com.offnine.food_springboot.response.UserResponse;

public interface UserService {
     UserResponse registerUser(UserRequest request);
    String findByUserId();
}
