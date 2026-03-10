package com.example.inventory_system.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Map;
@AllArgsConstructor
@Setter
@Getter
public class AddNewProduct {
    private String name;
    private String description;
    private Map<String,String> attributes;
    private BigInteger sellingPrice;
    private String category;
    private int quantityInStock;
    private String unitOfMeasurement;
}
