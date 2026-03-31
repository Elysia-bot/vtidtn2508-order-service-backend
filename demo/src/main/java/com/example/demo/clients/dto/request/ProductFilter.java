package com.example.demo.clients.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ProductFilter {
    private List<String> ids;
}
