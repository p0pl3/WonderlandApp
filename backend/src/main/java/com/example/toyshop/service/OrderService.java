package com.example.toyshop.service;

import com.example.toyshop.dto.order.OrderItemCreateDTO;
import com.example.toyshop.entity.Order;
import com.example.toyshop.entity.OrderItem;
import com.example.toyshop.entity.User;
import com.example.toyshop.exception.NotEnoughProductException;
import com.example.toyshop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository repository;
    private OrderItemService orderItemService;

    public Order create(Order order, List<OrderItemCreateDTO> orderItemsDto, User user) {
        LocalDate now = LocalDate.now();
        order.setBuyer(user);
        order.setCreationDate(now);
        repository.save(order);
        List<OrderItem> orderItems = orderItemsDto.stream()
                .map(orderItem -> {
                    try {
                        return orderItemService.create(orderItem.getProductId(), order, orderItem.getQuantity());
                    } catch (NotEnoughProductException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        return repository.save(order);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public List<Order> findAllByUserId(Long id) {
        return repository.findAllByBuyerId(id);
    }

    public Order findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Order update(Order order) {
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
