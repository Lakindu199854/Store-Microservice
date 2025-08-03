package com.smartshop.inventory_service.service;

import com.smartshop.inventory_service.dto.InventoryResponseDTO;
import com.smartshop.inventory_service.entity.InventoryEntity;
import com.smartshop.inventory_service.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Slf4j
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

    @Transactional(readOnly = true )
    public List<InventoryResponseDTO> getIsInStock( List<String> skuCodeList){
        List<InventoryEntity> inventoryEntities=inventoryRepository.findBySkuCodeIn(skuCodeList);
        return inventoryEntities.stream().map(inventoryEntity-> InventoryResponseDTO.builder()
                .isInStock((inventoryEntity.getQuantity()>0))
                .skuCode(inventoryEntity.getSkuCode())
                .build()).toList();
    }

}
