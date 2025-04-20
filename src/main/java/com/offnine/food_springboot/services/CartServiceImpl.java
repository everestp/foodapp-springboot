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

    @Override
    public CartResponse getCart() {
        String loggedInUserId = userService.findByUserId();
     CartEntity entity =   cartRepo.findByUserId(loggedInUserId).orElse(new CartEntity(null,loggedInUserId, new HashMap<>()));
        return convertToCartResponse(entity);
    }

    @Override
    public void clearCart() {
        String loggedInUserId = userService.findByUserId();
        cartRepo.deleteByUserId(loggedInUserId);

    }

    @Override
    public CartResponse removeFromCart(CartRequest cartRequest) {
        String loggedInUserId = userService.findByUserId();
     CartEntity entity = cartRepo.findByUserId(loggedInUserId).orElseThrow(()->new RuntimeException("Cart not found"));
Map<String,Integer> cartItems = entity.getItems();
if(cartItems.containsKey(cartRequest.getFoodId())){
    int currentQty = cartItems.get(cartRequest.getFoodId());
    if(currentQty >0){
        cartItems.put(cartRequest.getFoodId(), currentQty-1);
    }
    else{
        cartItems.remove(cartRequest.getFoodId());
    }
     entity =cartRepo.save(entity);

}
        return  convertToCartResponse(entity);
    }

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
