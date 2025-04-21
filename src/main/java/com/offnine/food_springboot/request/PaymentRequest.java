package com.offnine.food_springboot.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    String PaymentStatus;
    String TransactionCode;
}
