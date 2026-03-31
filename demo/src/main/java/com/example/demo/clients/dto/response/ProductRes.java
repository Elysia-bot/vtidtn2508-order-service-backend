package com.example.demo.clients.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRes {
    private String id;
    private String name;
    private Integer price;
    private Integer stock;
    private String categoryId;
}
