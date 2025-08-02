package com.smartshop.inventory_service.repository;

import com.smartshop.inventory_service.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository <InventoryEntity,Long> {

    Optional<InventoryEntity> findBySkuCode(String skuCode);
}
