package com.offnine.food_springboot.request;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Data
@Builder
public class UserRequest {
    private String name;
    private String email;
    private String password;

}
