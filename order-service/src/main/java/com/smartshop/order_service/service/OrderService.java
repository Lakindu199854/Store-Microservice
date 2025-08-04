package com.smartshop.order_service.service;

import com.smartshop.order_service.dto.InventoryResponseDTO;
import com.smartshop.order_service.dto.OrderLineItemsDTO;
import com.smartshop.order_service.dto.OrderRequestDTO;
import com.smartshop.order_service.dto.OrderResponseDTO;
import com.smartshop.order_service.model.OrderEntity;
import com.smartshop.order_service.model.OrderLineItemEntity;
import com.smartshop.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void createOrder(OrderRequestDTO orderRequestDTO) {
        OrderEntity orderEntity=OrderEntity.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequestDTO.getOrdeLineItemDTOs()
                        .stream()
                        .map(this::convertToEntity)
                        .toList())
                .build();

        List<String> skuCodes=orderEntity.getOrderLineItemsList().stream().map(OrderLineItemEntity::getSkuCode).toList();

        InventoryResponseDTO[] inventoryResponseDTOListArray=webClientBuilder.build().get()
                        .uri("http://inventoy-service:8082/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("sku-codes", skuCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponseDTO[].class)
                        .block();

        boolean allProductsInStock=Arrays.stream(inventoryResponseDTOListArray)
                .allMatch(InventoryResponseDTO::getIsInStock);

        log.info("Value of the allProductsInStock is :{}",allProductsInStock);

        if(allProductsInStock){
            orderRepository.save(orderEntity);
        }else{
            throw new IllegalArgumentException("Item quantity is 0");
        }
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