package com.example.demo.clients.impl;

import com.example.demo.clients.ProductClient;
import com.example.demo.clients.dto.request.ProductFilter;
import com.example.demo.clients.dto.response.ProductRes;
import com.example.demo.common.BaseResponse;
import com.example.demo.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClientImpl implements ProductClient {

    private final WebClient.Builder webClientBuilder;


    @Override
    public List<ProductRes> search(ProductFilter productFilter) {
        BaseResponse<List<ProductRes>> response = webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/api/v1/products/search")
                .bodyValue(productFilter)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<List<ProductRes>>>() {
                })
                .block();
        if (response == null){
            throw new ApplicationException("call product service error");
        }
        return response.getData();
    }
}
