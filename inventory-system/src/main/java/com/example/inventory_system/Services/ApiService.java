package com.example.inventory_system.Services;

import com.example.inventory_system.Dtos.AddNewProduct;
import com.example.inventory_system.Dtos.StockChangeRecord;
import com.example.inventory_system.Entities.Attribute;
import com.example.inventory_system.Entities.Category;
import com.example.inventory_system.Entities.Product;
import com.example.inventory_system.Entities.StockChange;
import com.example.inventory_system.Repositories.CategoryRepo;
import com.example.inventory_system.Repositories.ProductRepo;
import com.example.inventory_system.Repositories.StockChangeRepo;
import com.example.inventory_system.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApiService {
    private CategoryRepo categoryRepo;
    private ProductRepo productRepo;
    private UserRepo userRepo;
    private StockChangeRepo stockChangeRepo;
    public void addCategory(String name,String description){
        Category category=new Category();
        category.setName(name);
        category.setDescription(description);
        categoryRepo.save(category);

    }

    public  void  addNewProduct(AddNewProduct newProduct){
         Product product=new Product();
         product.setName(newProduct.getName());
         product.setDescription(newProduct.getName());
         product.setCategory(categoryRepo.findByName(newProduct.getCategory()).orElseThrow());
         List<Attribute> attributes=new ArrayList<>();
         for(String type:newProduct.getAttributes().keySet()){
             attributes.add(new Attribute(product,type,newProduct.getAttributes().get(type)));
         }
         product.setAttributes(attributes);
         product.setSellingPrice(newProduct.getSellingPrice());
         product.setQuantityInStock(newProduct.getQuantityInStock());
         product.setUnitOfMeasurement(newProduct.getUnitOfMeasurement());
         productRepo.save(product);
    }

    public void recordStockChange(StockChangeRecord record){
        StockChange stockChange=new StockChange();
        stockChange.setActionTime(LocalDateTime.now());
        stockChange.setReason(record.getReason());
        stockChange.setQuantity(record.getQuantity());
        stockChange.setAgent(userRepo.findByUsername(record.getAgent()).orElseThrow());
        stockChange.setProduct(productRepo.findByName(record.getProduct()).orElseThrow());
        stockChangeRepo.save(stockChange);

    }
}
