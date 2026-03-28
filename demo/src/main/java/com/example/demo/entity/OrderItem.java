package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

public class OrderItem extends BaseEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private  Integer quantity;


}
