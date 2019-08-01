package com.example.example.controller;

import com.example.example.dto.ProductDto;
import com.example.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/smartphone")
public class ProductController {

    @Autowired
    private  ProductService productService;
    @GetMapping("/list")
    public ResponseEntity<?> getListProduct(){
        try{
            List<ProductDto> list =  productService.listProduct();
            if (list==null || list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto pro){
        try{
            boolean save =  productService.save(pro);
            if (!save){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(save,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
