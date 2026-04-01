package com.example.demo.clients.impl;

import com.example.demo.clients.PromotionClient;
import com.example.demo.clients.dto.request.PromotionFind;
import com.example.demo.clients.dto.response.ProductRes;
import com.example.demo.clients.dto.response.PromotionResponse;
import com.example.demo.common.BaseResponse;
import com.example.demo.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PromotionClientImpl implements PromotionClient {
    private final WebClient.Builder webClientBuilder;


    @Override
    public PromotionResponse find(PromotionFind promotion) {
        BaseResponse<PromotionResponse> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/v1/promotions/{code}", promotion.getCode())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<PromotionResponse>>() {
                })
                .block();
        if (response == null){
            throw new ApplicationException("call promotion service error");
        }
        return response.getData();
    }
}
