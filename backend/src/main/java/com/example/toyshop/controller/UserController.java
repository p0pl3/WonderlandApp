package com.example.toyshop.controller;

import com.example.toyshop.dto.user.UserCreateDTO;
import com.example.toyshop.dto.user.UserDTO;
import com.example.toyshop.dto.user.UserDetailDTO;
import com.example.toyshop.entity.User;
import com.example.toyshop.mapper.UserMapper;
import com.example.toyshop.security.AuthenticatedUserService;
import com.example.toyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final AuthenticatedUserService authenticatedUserService;
    private final UserMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDetailDto(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<UserDTO> getPersonInformation() {
        System.out.println("123");
        User user = authenticatedUserService.getAuthenticatedPerson();
        System.out.println(user);
        return ResponseEntity.ok(mapper.toDto(user));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<UserDetailDTO> update(@PathVariable Long id, @RequestBody UserCreateDTO dto) {
        return ResponseEntity.ok(mapper.toDetailDto(service.update(mapper.update(dto, service.findById(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
