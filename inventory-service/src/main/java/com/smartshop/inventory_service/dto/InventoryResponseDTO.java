package com.smartshop.inventory_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InventoryResponseDTO {


    private Long id;
    private String  skuCode;
    private BigDecimal quantity;
}
