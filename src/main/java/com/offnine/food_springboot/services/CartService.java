package com.offnine.food_springboot.services;

import com.offnine.food_springboot.request.CartRequest;
import com.offnine.food_springboot.response.CartResponse;

public interface CartService {
 CartResponse addToCart(CartRequest request);
 CartResponse getCart();
 void clearCart();
  CartResponse    removeFromCart(CartRequest cartRequest);
}
