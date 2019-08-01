package com.example.example.controller;

import com.example.example.dto.ProductDto;
import com.example.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/smartphone")
public class ProductController {

    @Autowired
    private  ProductService productService;
    @GetMapping("/list")
    public List<ProductDto> getListProduct()  throws Exception{
        return productService.listProduct();
    }
    @PostMapping("/save")
    public boolean saveProduct(@RequestBody ProductDto pro){
        return productService.save(pro);
    }
}
