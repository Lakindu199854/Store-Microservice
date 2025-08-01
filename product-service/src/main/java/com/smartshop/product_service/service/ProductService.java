package com.smartshop.product_service.service;

import com.smartshop.product_service.dto.CreateProductRequestDTO;
import com.smartshop.product_service.dto.ProductResponseDTO;
import com.smartshop.product_service.model.ProductEntity;
import com.smartshop.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity createProduct(CreateProductRequestDTO createProductRequestDTO){
        ProductEntity productEntity = ProductEntity.builder()
                .name(createProductRequestDTO.getName())
                .price(createProductRequestDTO.getPrice())
                .description(createProductRequestDTO.getDescription())
                .build();
        ProductEntity savedEntity=productRepository.save(productEntity);
        log.info("ProductEntity Created:{}",savedEntity.getId());
        return savedEntity;
    }

    public ProductResponseDTO getProductById(String id){
        ProductEntity productEntity = productRepository.findById(id).get();

        ProductResponseDTO responseDTO=ProductResponseDTO.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .description(productEntity.getDescription())
                        .build();
        log.info("Fetched Product:{}",productEntity.getId());
        return responseDTO;
    }

    public List<ProductResponseDTO> getAllProducts(){
        List<ProductEntity> products = productRepository.findAll();
        log.info("Fetched all Products:{}",products);
        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponseDTO mapToProductResponse(ProductEntity productEntity) {
        return ProductResponseDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .build();

    }


}
