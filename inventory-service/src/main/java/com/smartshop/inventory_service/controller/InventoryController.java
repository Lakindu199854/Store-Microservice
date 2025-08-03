package com.smartshop.inventory_service.controller;

import com.smartshop.inventory_service.dto.InventoryResponseDTO;
import com.smartshop.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<InventoryResponseDTO>> isInStock(@RequestParam("sku-codes") List<String> skuCodeList){

        List<InventoryResponseDTO> inventoryResponseDTOList=inventoryService.getIsInStock(skuCodeList);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryResponseDTOList);
    }
}
