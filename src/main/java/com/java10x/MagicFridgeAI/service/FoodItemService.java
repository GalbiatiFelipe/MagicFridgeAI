package com.java10x.MagicFridgeAI.service;

import com.java10x.MagicFridgeAI.entity.FoodItem;
import com.java10x.MagicFridgeAI.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;


    public FoodItem create(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> findAll() {
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> findById(Long id) {
        return foodItemRepository.findById(id);
    }

    public Optional<FoodItem> update(Long id, FoodItem foodItem) {
        Optional<FoodItem> optFoodItem = foodItemRepository.findById(id);
        if (optFoodItem.isPresent()) {
            FoodItem updatedFoodItem = optFoodItem.get();
            updatedFoodItem.setName(foodItem.getName());
            updatedFoodItem.setType(foodItem.getType());
            updatedFoodItem.setQuantity(foodItem.getQuantity());
            updatedFoodItem.setValidTo(foodItem.getValidTo());

            foodItemRepository.save(updatedFoodItem);
            return Optional.of(updatedFoodItem);
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        foodItemRepository.deleteById(id);
    }

}
