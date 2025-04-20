package com.offnine.food_springboot.services;

import com.offnine.food_springboot.Repo.CartRepo;
import com.offnine.food_springboot.entity.CartEntity;
import com.offnine.food_springboot.request.CartRequest;
import com.offnine.food_springboot.response.CartResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final CartRepo cartRepo;

    @Override
    public CartResponse addToCart(CartRequest request) {
  String loggedInUserId = userService.findByUserId();
  Optional<CartEntity> cartOptional = cartRepo.findByUserId(loggedInUserId);
CartEntity cart = cartOptional.orElseGet(() -> new CartEntity(loggedInUserId,new HashMap<>()));
 Map<String,Integer> cartItems = cart.getItems();
 cartItems.put(request.getFoodId(),cartItems.getOrDefault(request.getFoodId(),0) + 1);
 cart.setItems(cartItems);
  cart = cartRepo.save(cart);
  return  convertToCartResponse(cart);


    }
    private CartResponse convertToCartResponse(CartEntity cart) {
       return  CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(cart.getItems())
                .build();

    }
}
