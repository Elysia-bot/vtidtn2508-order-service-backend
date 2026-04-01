package com.example.demo.controller;

import com.example.demo.clients.dto.response.ProductRes;
import com.example.demo.clients.dto.response.PromotionResponse;
import com.example.demo.dto.request.CreateOrderRequest;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{code}")
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request, @PathVariable String code){
        return ResponseEntity.ok(orderService.create(request, code));
    }

}
