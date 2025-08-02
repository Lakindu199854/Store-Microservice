package com.smartshop.inventory_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryRequestDTO {
    private String  skuCode;
    private BigDecimal quantity;


}
