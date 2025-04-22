package com.offnine.food_springboot.controllers;


import com.offnine.food_springboot.request.CartRequest;
import com.offnine.food_springboot.response.CartResponse;
import com.offnine.food_springboot.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    @PostMapping
    public  CartResponse addToCart(@RequestBody CartRequest req) {
String  fooId = req.getFoodId();
if(fooId == null || fooId.isEmpty()) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FoodId not found");
}
 return cartService.addToCart(req);

    }
@GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
}

    @DeleteMapping
@ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart() {
        cartService.clearCart();
}
@PostMapping("/remove")
    public  CartResponse removeFromCart(@RequestBody CartRequest req) {
    String  fooId = req.getFoodId();
    if(fooId == null || fooId.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FoodId not found");
    }
   return cartService.removeFromCart(req);



}

}
