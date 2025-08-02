package com.smartshop.order_service.service;

import com.smartshop.order_service.dto.OrderLineItemsDTO;
import com.smartshop.order_service.dto.OrderRequestDTO;
import com.smartshop.order_service.dto.OrderResponseDTO;
import com.smartshop.order_service.model.OrderEntity;
import com.smartshop.order_service.model.OrderLineItemEntity;
import com.smartshop.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderRequestDTO orderRequestDTO) {
        OrderEntity orderEntity=OrderEntity.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequestDTO.getOrdeLineItemDTOs()
                        .stream()
                        .map(this::convertToEntity)
                        .toList())
                .build();
        orderRepository.save(orderEntity);

    }
    public OrderLineItemEntity convertToEntity(OrderLineItemsDTO orderLineItemsDTO){
        return OrderLineItemEntity.builder()
                .orderLineId(orderLineItemsDTO.getId())
                .skuCode(orderLineItemsDTO.getSkuCode())
                .price(orderLineItemsDTO.getPrice())
                .quantity(orderLineItemsDTO.getQuantity())
                .build();
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<OrderEntity> orderEntityList=orderRepository.findAll();
        List<OrderResponseDTO> orderResponseDTOList=orderEntityList.stream().map(this::convertToResponseDTO).toList();

        return orderResponseDTOList;
    }
    public OrderResponseDTO  convertToResponseDTO(OrderEntity orderEntity){
       return OrderResponseDTO.builder()
                .orderId(orderEntity.getOrderId())
                .orderNumber(orderEntity.getOrderNumber())
                .orderLineItemsList(orderEntity.getOrderLineItemsList())
                .build();
    }





}