package com.smartshop.order_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponseDTO {

    private String  skuCode;
    private Boolean isInStock;
}
