package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

public class Order extends BaseEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "total_amount")
    private Integer totalAmount;

}
