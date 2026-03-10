package com.example.inventory_system.Controllers;


import com.example.inventory_system.Dtos.AddNewProduct;
import com.example.inventory_system.Services.ApiService;
import com.example.inventory_system.Services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private ApiService apiService;

    @PostMapping("/addCategory")
    public  void  addCategory(String name,String description){
        apiService.addCategory(name,description);
    }
    @PostMapping("/addProduct")
    public void addProduct(@RequestBody AddNewProduct newProduct){
          apiService.addNewProduct(newProduct);
    }
    @PostMapping("/logChange")
    public void recordStockChange(){


    }

}
