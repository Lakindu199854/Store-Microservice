package com.smartshop.inventory_service.repository;

import com.smartshop.inventory_service.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository <InventoryEntity,Long> {

    List<InventoryEntity> findBySkuCodeIn(List<String> skuCode);
}