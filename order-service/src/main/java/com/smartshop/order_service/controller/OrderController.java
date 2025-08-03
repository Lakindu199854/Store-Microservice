package com.smartshop.order_service.controller;

import com.smartshop.order_service.dto.OrderRequestDTO;
import com.smartshop.order_service.dto.OrderResponseDTO;
import com.smartshop.order_service.model.OrderEntity;
import com.smartshop.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        try{
            orderService.createOrder(orderRequestDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Order created successfully");
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        List<OrderResponseDTO> responseDTOS=orderService.getAllOrders();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDTOS);
    }
}
