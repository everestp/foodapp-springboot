package com.offnine.food_springboot.controllers;


import com.offnine.food_springboot.request.AuthRequest;
import com.offnine.food_springboot.response.AuthResponse;
import com.offnine.food_springboot.services.AppUserDetailsService;
import com.offnine.food_springboot.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.webauthn.api.AuthenticatorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor



public class AuthController {
private final AuthenticationManager authenticationManager;
private final AppUserDetailsService userDetailsService;
private final JwtUtil jwtUtil;
    @PostMapping("/login")
    public AuthResponse  login(@RequestBody AuthRequest request){
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
 final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
 final String jwtToken = jwtUtil.generateToken(userDetails);
 return  new AuthResponse(request.getEmail(), jwtToken);
 
    }



}
