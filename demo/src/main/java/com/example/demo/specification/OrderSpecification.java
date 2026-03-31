package com.example.demo.specification;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class OrderSpecification {

    public static Specification<Order> hasIds(List<String> ids) {
        return (root, query, cb) -> {
            if (ids == null || ids.isEmpty()) {
                return null;
            }
            return root.get("id").in(ids);
        };
    }
}
