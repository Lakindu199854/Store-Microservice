package com.smartshop.inventory_service.controller;

import com.smartshop.inventory_service.dto.InventoryRequestDTO;
import com.smartshop.inventory_service.dto.InventoryResponseDTO;
import com.smartshop.inventory_service.entity.InventoryEntity;
import com.smartshop.inventory_service.repository.InventoryRepository;
import com.smartshop.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/inventory")
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

//    @PostMapping
//    public ResponseEntity <String> addInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO){
//        inventoryService.createInventory(inventoryRequestDTO);
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body("Inventory Item Created");
//    }

    @GetMapping("/{sku-code}")
    public ResponseEntity<String> isInStock(@PathVariable("sku-code") String skuCode){

        Boolean isInStock=inventoryService.getIsInStock(skuCode);
        return ResponseEntity.status(HttpStatus.OK).body(isInStock == true?"In stock":"Out of stock");
    }
}
