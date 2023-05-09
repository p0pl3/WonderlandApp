package com.example.toyshop.dto.order;

import com.example.toyshop.entity.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderCreateDTO {
    private String address;
    private String phone;
    private List<OrderItemCreateDTO> orderItems;
    private OrderStatus status;
    private Float total;
}
