package com.example.inventory_system.Dtos;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private List<String> attributes;
    @DecimalMin(value="1.00",message = "selling price can't be less than 1 ETB.")
    private BigDecimal sellingPrice;
    @NotNull
    private long categoryId;
    @Size(min=0)
    private int quantityInStock;
    @NotBlank
    private String unitOfMeasurement;
}
