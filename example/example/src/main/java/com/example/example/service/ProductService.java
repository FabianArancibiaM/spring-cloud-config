package com.example.example.service;

import com.example.example.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> listProduct() throws Exception;
    boolean save(ProductDto dto);
}
