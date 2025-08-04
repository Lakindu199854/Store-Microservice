package com.smartshop.inventory_service;

import com.smartshop.inventory_service.entity.InventoryEntity;
import com.smartshop.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			InventoryEntity inventoryEntity1 = new InventoryEntity();
			inventoryEntity1.setSkuCode("Iphone 13");
			inventoryEntity1.setQuantity(10);

			InventoryEntity inventoryEntity2 = new InventoryEntity();
			inventoryEntity2.setSkuCode("Iphone 14");
			inventoryEntity2.setQuantity(0);

			inventoryRepository.save(inventoryEntity1);
			inventoryRepository.save(inventoryEntity2);
		};
	}
}
