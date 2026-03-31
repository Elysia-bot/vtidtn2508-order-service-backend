package com.example.demo.service.impl;

import com.example.demo.clients.ProductClient;
import com.example.demo.clients.dto.request.ProductFilter;
import com.example.demo.clients.dto.response.ProductRes;
import com.example.demo.common.OrderStatus;
import com.example.demo.dto.request.CreateOrderRequest;
import com.example.demo.dto.request.OrderItemDTO;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.exception.ApplicationException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final ProductClient productClient;

    @Override
    @Transactional
    public OrderResponse create(CreateOrderRequest request) {

        //nhận order item từ request và đưa các id của item vào list
        List<OrderItemDTO> orderItemDTOs = request.getOrderItems();
        List<String> productIds = new ArrayList<>();

        for(OrderItemDTO orderItemDTO : orderItemDTOs){
            productIds.add(orderItemDTO.getProductId());
        }

        // đưa list id vào productfilter(request check product) và call api của product service
        //để check id product có tồn tại không
        ProductFilter productFilter = ProductFilter.builder().ids(productIds).build();
        List<ProductRes> productResList = productClient.search(productFilter);

        //pro res trả về các attri của product khi tìm thấy
        //gán vào map để tìm nhanh O(1)
        Map<String,ProductRes> productPriceMap = new HashMap<>();
        for(ProductRes product : productResList){
            productPriceMap.put(product.getId(), product);
        }

        //tạo order và lưu các trường thông tin
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setStatus(OrderStatus.NEW.name());
        order.setTotalAmount(0);
        Order savedOrder = orderRepository.save(order);

        //tạo total amount và list order item để gán vào order item repo
        int totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        //duyệt qua các phần tử trong list item đã gán từ B1
        //gán các thuộc tính cho mỗi order item để lưu vào order item repo
        // ở mỗi product check giá để cộng vào total amount
        for(OrderItemDTO orderItemDTO : orderItemDTOs){

            //valid : không tìm thấy product
            // valid : stock không đủ đáp ứng
            ProductRes product = productPriceMap.get(orderItemDTO.getProductId());
            if(product == null){
                throw new ApplicationException("product not found");
            }
            if(product.getStock() < orderItemDTO.getQuantity()){
                throw new ApplicationException("not enough stock");
            }

            OrderItem item = new OrderItem();
            item.setOrderId(savedOrder.getId());
            item.setProductId(orderItemDTO.getProductId());
            item.setQuantity(orderItemDTO.getQuantity());
            item.setPrice(productPriceMap.get(orderItemDTO.getProductId()).getPrice());


            orderItems.add(item);
            totalAmount += product.getPrice() * orderItemDTO.getQuantity();
        }

        //update order
        //lưu order item vao repo

        orderItemRepository.saveAll(orderItems);
        savedOrder.setTotalAmount(totalAmount);
        return orderMapper.to(orderRepository.save((savedOrder)));







        //http://localhost:8080/api/v1/products/search

    }
}
