package com.smartshop.order_service.dto;

import com.smartshop.order_service.model.OrderLineItemEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private String orderNumber;
    private List<OrderLineItemEntity> orderLineItemsList;
}
