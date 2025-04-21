package com.offnine.food_springboot.controllers;

import com.offnine.food_springboot.request.OrderRequest;
import com.offnine.food_springboot.request.PaymentRequest;
import com.offnine.food_springboot.response.OrderResponse;
import com.offnine.food_springboot.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {



    private final OrderService orderService;

    @PostMapping("/create")
    private OrderResponse createOrderwithPayment(@RequestBody OrderRequest request){
        OrderResponse resposne = orderService.createOrderWithPayment(request);
        return resposne;

    }
    @PatchMapping("/{orderId}/paymentStatus")
    public void updatePaymentStatus(@PathVariable String orderId, @RequestBody PaymentRequest paymentData) {
        orderService.updatePaymentStatus(orderId, paymentData);
    }
    @PatchMapping("/status/{orderId}")
    public void updateOrderStatus(@PathVariable String orderId , @RequestParam  String status) {
        orderService.updateOrderStatus(orderId, status);

    }

    @GetMapping
public List<OrderResponse> getOrders(){
        return orderService.getUserOrders();
    }



    @DeleteMapping("/delete/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String orderId) {
        orderService.removeOrder(orderId);
    }
@GetMapping("/all")
    public List<OrderResponse> getOrdersOfAllUsers() {
        return orderService.getOrdersOfAllUsers();
}

}
