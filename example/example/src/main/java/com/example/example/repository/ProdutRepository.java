package com.example.example.repository;

import com.example.example.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutRepository extends JpaRepository<ProductModel, Long> {
}
