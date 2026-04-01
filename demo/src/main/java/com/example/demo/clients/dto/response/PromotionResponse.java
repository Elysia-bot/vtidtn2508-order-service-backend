package com.example.demo.clients.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionResponse {
    private String id;
    private String name;
    private String code;
    private String discountType;
    private Integer discountValue;
    private Integer minOrderValue;

}
