package com.java10x.MagicFridgeAI.controller;

import com.java10x.MagicFridgeAI.entity.FoodItem;
import com.java10x.MagicFridgeAI.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fooditem")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    public ResponseEntity<FoodItem> create(@RequestBody FoodItem foodItem) {
        FoodItem fiCreated = foodItemService.create(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(fiCreated);
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> findAll() {
        return ResponseEntity.ok(foodItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> findById(@PathVariable Long id) {
        return foodItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> update(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        return foodItemService.update(id, foodItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodItemService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
