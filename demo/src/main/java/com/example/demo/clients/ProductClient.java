package com.example.demo.clients;

import com.example.demo.clients.dto.request.ProductFilter;
import com.example.demo.clients.dto.response.ProductRes;

import java.util.List;

public interface ProductClient {
    List<ProductRes> search(ProductFilter productFilter);
}
