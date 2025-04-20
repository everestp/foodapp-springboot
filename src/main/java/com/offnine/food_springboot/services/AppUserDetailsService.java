package com.offnine.food_springboot.services;

import com.offnine.food_springboot.Repo.UserRepo;
import com.offnine.food_springboot.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AppUserDetailsService  implements UserDetailsService {
    private  final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     UserEntity user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not  found"));
      return  new User(user.getEmail(),user.getPassword(), Collections.emptyList());
    }
}
