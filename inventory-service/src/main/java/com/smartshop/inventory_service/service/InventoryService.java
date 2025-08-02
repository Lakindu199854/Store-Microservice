package com.smartshop.inventory_service.service;

import com.smartshop.inventory_service.dto.InventoryRequestDTO;
import com.smartshop.inventory_service.dto.InventoryResponseDTO;
import com.smartshop.inventory_service.entity.InventoryEntity;
import com.smartshop.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

//    public void createInventory(InventoryRequestDTO inventoryRequestDTO){
//        InventoryEntity inventoryEntity=InventoryEntity.builder()
//                .quantity(inventoryRequestDTO.getQuantity())
//                .skuCode(inventoryRequestDTO.getSkuCode())
//                .build();
//        inventoryRepository.save(inventoryEntity);
//    }
//
//    public List<InventoryResponseDTO> getAllInventories(){
//        List<InventoryEntity> entityList=inventoryRepository.findAll();
//        return entityList.stream().map(this::convertToResPonseDTO).toList();
//    }
//
//    public InventoryResponseDTO convertToResPonseDTO(InventoryEntity inventoryEntity){
//        return InventoryResponseDTO.builder()
//                .id(inventoryEntity.getId())
//                .quantity(inventoryEntity.getQuantity())
//                .skuCode(inventoryEntity.getSkuCode())
//                .build();
//    }


    public boolean getIsInStock(String skuCode){
       return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
