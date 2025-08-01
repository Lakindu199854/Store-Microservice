package com.smartshop.product_service.controller;

import com.smartshop.product_service.dto.CreateProductRequestDTO;
import com.smartshop.product_service.dto.ProductResponseDTO;
import com.smartshop.product_service.model.ProductEntity;
import com.smartshop.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductEntity> createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO){
        ProductEntity saved=productService.createProduct(createProductRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/id")
        public ResponseEntity<ProductResponseDTO> getProduct(@RequestParam String id){
        ProductResponseDTO responseDTO=productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> productList=productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
}
