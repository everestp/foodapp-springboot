package com.offnine.food_springboot.services;

import com.offnine.food_springboot.Repo.UserRepo;
import com.offnine.food_springboot.entity.UserEntity;
import com.offnine.food_springboot.request.UserRequest;
import com.offnine.food_springboot.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements  UserService{
    private  final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;
    @Override
    public UserResponse registerUser(UserRequest request) {
       UserEntity newUser = convertToUserEntity(request);
       newUser = userRepo.save(newUser);
        return convertToUserResponse(newUser);
    }

    @Override
    public String findByUserId() {
       String logggedInEmail = authenticationFacade.getAuthentication().getName();
      UserEntity loggedInUser =  userRepo.findByEmail(logggedInEmail).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
      return  loggedInUser.getId();
    }

    private UserEntity convertToUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(passwordEncoder.encode(userRequest.getPassword())).build();

    }

    private UserResponse convertToUserResponse(UserEntity registered) {
        return UserResponse.builder()
                .id(registered.getId())
                .name(registered.getName())
                .email(registered.getEmail())
                .build();
    }
}
