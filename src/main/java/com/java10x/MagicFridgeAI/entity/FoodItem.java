package com.java10x.MagicFridgeAI.entity;

import com.java10x.MagicFridgeAI.enums.FoodItemEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "food_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FoodItemEnum type;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "valid_to")
    private LocalDate validTo;

}
