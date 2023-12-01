package com.example.toyshop.service;


import com.example.toyshop.entity.Order;
import com.example.toyshop.entity.OrderItem;
import com.example.toyshop.entity.Product;
import com.example.toyshop.exception.NotEnoughProductException;
import com.example.toyshop.exception.ProductNotFoundException;
import com.example.toyshop.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository repository;
    private final ProductService productService;

    public OrderItem create(Long productId, Order order, Short quantity) throws NotEnoughProductException {
        Product product = productService.findById(productId);
        if (product.getAmount() < quantity) {
            throw new NotEnoughProductException("not enough" + product.getTitle());
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setQuantity(quantity);
        return repository.save(orderItem);
    }

    public List<OrderItem> findAll() {
        return repository.findAll();
    }

    public List<OrderItem> findAllByOrderId(Long id) {
        return repository.findAllByOrderId(id);
    }
}
