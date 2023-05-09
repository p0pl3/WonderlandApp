package com.example.toyshop.service;

import com.example.toyshop.dto.order.OrderCreateDTO;
import com.example.toyshop.dto.order.OrderItemListDTO;
import com.example.toyshop.dto.order.OrderListDTO;
import com.example.toyshop.entity.Order;
import com.example.toyshop.entity.OrderItem;
import com.example.toyshop.entity.User;
import com.example.toyshop.repository.OrderRepository;
import com.example.toyshop.mapper.OrderItemMapper;
import com.example.toyshop.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private OrderMapper mapper;
    private OrderItemMapper mapperItem;
    private UserService userService;
    private OrderItemService orderItemService;

    public OrderListDTO create(OrderCreateDTO dto, User user) {
        LocalDate now = LocalDate.now();
        Order order = mapper.fromCreateDto(dto);
        repository.save(order);
        order.setBuyer(user);
        order.setCreationDate(now);
        List<OrderItem> orderItems = dto.getOrderItems().stream().map(orderItem -> orderItemService.create(orderItem, order)).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        return mapper.toListDto(repository.save(order));
    }

    public List<OrderListDTO> findAll() {
        return repository.findAll().stream().map(mapper::toListDto).collect(Collectors.toList());
    }

    public List<OrderListDTO> findAllByUserId(Long id) {
        return repository.findAllByBuyerId(id).stream().map(mapper::toListDto).collect(Collectors.toList());
    }

    public OrderListDTO findById(Long id) {
        return mapper.toListDto(repository.findById(id).orElse(null));
    }

    public List<OrderItemListDTO> findAllByOrderId(Long id) {
        return Objects.requireNonNull(repository.findById(id).orElse(null)).getOrderItems().stream().map(mapperItem::toListDto).collect(Collectors.toList());
    }

    public Order update(Order order) {
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
