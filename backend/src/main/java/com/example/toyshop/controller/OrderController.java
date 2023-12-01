package com.example.toyshop.controller;

import com.example.toyshop.dto.order.OrderCreateDTO;
import com.example.toyshop.dto.order.OrderItemListDTO;
import com.example.toyshop.dto.order.OrderListDTO;
import com.example.toyshop.entity.User;
import com.example.toyshop.mapper.OrderItemMapper;
import com.example.toyshop.mapper.OrderMapper;
import com.example.toyshop.security.AuthenticatedUserService;
import com.example.toyshop.service.OrderItemService;
import com.example.toyshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService service;
    private final OrderItemService orderItemService;
    private final AuthenticatedUserService authenticatedUserService;
    private OrderMapper mapper;
    private OrderItemMapper orderItemMapper;

    @PostMapping("/")
    public ResponseEntity<OrderListDTO> create(@RequestBody OrderCreateDTO dto) {
        User user = authenticatedUserService.getAuthenticatedPerson();
        return ResponseEntity.ok(mapper.toListDto(service.create(mapper.fromCreateDto(dto), dto.getOrderItems(), user)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderListDTO>> getAllOrders() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toListDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderListDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toListDto(service.findById(id)));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<OrderListDTO>> getAllUserOrdersById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAllByUserId(id).stream().map(mapper::toListDto).collect(Collectors.toList()));
    }

    @GetMapping("/user")
    public ResponseEntity<List<OrderListDTO>> getAllUserOrders() {
        User user = authenticatedUserService.getAuthenticatedPerson();
        return ResponseEntity.ok(service.findAllByUserId(user.getId()).stream().map(mapper::toListDto).collect(Collectors.toList()));
    }

    @GetMapping("/{oid}/items/list")
    public ResponseEntity<List<OrderItemListDTO>> getAllOrderItems(@PathVariable Long oid) {
        return ResponseEntity.ok(orderItemService.findAllByOrderId(oid).stream().map(orderItemMapper::toListDto).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
