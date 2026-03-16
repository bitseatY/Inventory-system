package com.example.inventory_system.Services;

import com.example.inventory_system.Dtos.ProductDto;
import com.example.inventory_system.Dtos.CategoryDto;
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

    public List<CategoryDto> fetchAllCategories(){
          return  categoryRepo.findAll()
                  .stream()
                  .map(category -> new CategoryDto(category.getId(),
                          category.getName(),category.getDescription())).toList();

    }

    public List<ProductDto>  fetchAllProductsInCategory(long catId){
        return  productRepo.findAllByCategoryId(catId)
                .stream().map(product -> new ProductDto(
                        product.getName(), product.getDescription(),
                        product.getAttributes().stream().map(Attribute::toString).toList(),
                        product.getSellingPrice(),product.getCategory().getId(),
                        product.getQuantityInStock(),product.getUnitOfMeasurement())).toList();

    }


    public CategoryDto addCategory(String name,String description){
        if(categoryRepo.findByName(name).isPresent()){
            throw new RuntimeException("category name already present.");
        }
        Category category=new Category();
        category.setName(name);
        category.setDescription(description);
        categoryRepo.save(category);
        return new CategoryDto(category.getId(),category.getName(),category.getDescription());

    }

    public  ProductDto  addNewProduct(ProductDto newProduct){
         if(productRepo.findByName(newProduct.getName()).isPresent()){
             throw new RuntimeException("product name already exists.");
         }
         Product product=new Product();
         product.setName(newProduct.getName());
         product.setDescription(newProduct.getDescription());
         product.setCategory(categoryRepo.findById(newProduct.getCategoryId()).orElseThrow());
         List<Attribute> attributes=new ArrayList<>();
         for(String attribute: newProduct.getAttributes()){
             String[] pair=attribute.split(":");
             attributes.add(new Attribute(product,pair[0],pair[1]));
         }
         product.setAttributes(attributes);
         product.setSellingPrice(newProduct.getSellingPrice());
         product.setQuantityInStock(newProduct.getQuantityInStock());
         product.setUnitOfMeasurement(newProduct.getUnitOfMeasurement());
         productRepo.save(product);
         return  newProduct;
    }

    public String recordStockChange(StockChangeRecord record){
        Product product=productRepo.findById(record.getProductId()).orElseThrow();
        if(product.getQuantityInStock()+ record.getQuantity()<0)
            throw new RuntimeException("negative stock can't be logged.");
        product.setQuantityInStock(product.getQuantityInStock()+ record.getQuantity());
        StockChange stockChange=new StockChange();
        stockChange.setActionTime(LocalDateTime.now());
        stockChange.setReason(record.getReason());
        stockChange.setAgent(userRepo.findById(record.getAgentId()).orElseThrow());
        stockChange.setProduct(productRepo.findById(record.getProductId()).orElseThrow());
        stockChangeRepo.save(stockChange);
        return "change successfully recorded";
    }


}
