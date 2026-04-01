package com.example.demo.clients;

import com.example.demo.clients.dto.request.PromotionFind;
import com.example.demo.clients.dto.response.PromotionResponse;

public interface PromotionClient {
    PromotionResponse find(PromotionFind promotion);
}
