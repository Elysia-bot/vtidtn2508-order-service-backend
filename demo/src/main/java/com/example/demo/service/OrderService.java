package com.example.demo.service;

import com.example.demo.dto.request.CreateOrderRequest;
import com.example.demo.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse create(CreateOrderRequest request, String code);

}
