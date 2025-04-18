package com.offnine.food_springboot.services;

import com.offnine.food_springboot.request.FoodRequest;
import com.offnine.food_springboot.response.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {
 String uploadFile(MultipartFile file);
 boolean deleteFile(String fileName);
 FoodResponse addFood(FoodRequest request , MultipartFile filer);
 List<FoodResponse> readFoods();
 FoodResponse readFood(String id);
 void deleteFood(String id);
}
