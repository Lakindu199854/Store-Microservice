package com.smartshop.order_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Table(name = "order_line")
public class OrderLineItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderLineId;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;



}
