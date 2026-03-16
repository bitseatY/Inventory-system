package com.example.inventory_system.Controllers;


import com.example.inventory_system.Dtos.CategoryDto;
import com.example.inventory_system.Dtos.ProductDto;
import com.example.inventory_system.Dtos.StockChangeRecord;
import com.example.inventory_system.Entities.Product;
import com.example.inventory_system.Entities.StockChange;
import com.example.inventory_system.Repositories.StockChangeRepo;
import com.example.inventory_system.Services.ApiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import org.springframework.ui.Model;


@AllArgsConstructor
@Controller
@RequestMapping("/api")
public class ApiController {
    private ApiService apiService;
    private StockChangeRepo stockChangeRepo;

    @PostMapping("/categories")
    public List<CategoryDto> fetchAllCategories(){
        return apiService.fetchAllCategories();
    }
    @PostMapping("/products")
    public List<ProductDto> fetchAllProducts(@RequestParam long catId){
        return apiService.fetchAllProductsInCategory(catId);
    }

    @PostMapping("/addCategory")
    public  CategoryDto addCategory(String name,String description){
       return    apiService.addCategory(name,description);
    }
    @PostMapping("/addProduct")
    public ProductDto addProduct(@RequestBody ProductDto newProduct){
         return apiService.addNewProduct(newProduct);
    }

    @PostMapping("/logChange")
    public String recordStockChange(@RequestBody StockChangeRecord record){
         return    apiService.recordStockChange(record);

    }
    @GetMapping("/viewLog")
    public String viewStockChangeRecord(Model model){
        List<StockChange> stockChanges=stockChangeRepo.findAll();
        model.addAttribute("StockChanges",stockChanges);
        return "log";
    }


}
