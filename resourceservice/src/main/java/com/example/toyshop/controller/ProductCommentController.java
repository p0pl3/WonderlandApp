package com.example.toyshop.controller;

import com.example.toyshop.dto.product_comment.ProductCommentCreateDTO;
import com.example.toyshop.dto.product_comment.ProductCommentDTO;
import com.example.toyshop.dto.product_comment.ProductCommentDetailDTO;
import com.example.toyshop.entity.User;
import com.example.toyshop.mapper.ProductCommentMapper;
import com.example.toyshop.security.AuthenticatedUserService;
import com.example.toyshop.service.ProductCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product/comments")
@AllArgsConstructor
public class ProductCommentController {
    private final ProductCommentService service;
    private final AuthenticatedUserService authenticatedUserService;
    private final ProductCommentMapper mapper;

    @PostMapping("/{pid}")
    public ResponseEntity<ProductCommentDetailDTO> create(@RequestBody ProductCommentCreateDTO dto, @PathVariable Long pid) {
        User user = authenticatedUserService.getAuthenticatedPerson();
        return ResponseEntity.ok(mapper.toDetailDto(service.create(mapper.fromCreateDto(dto), pid, user)));
    }

    @GetMapping("/{pid}/list")
    public ResponseEntity<List<ProductCommentDTO>> getAllProductComments(@PathVariable Long pid,
                                                                         @RequestParam(name = "min_rating", required = false) Float min_rating,
                                                                         @RequestParam(name = "max_rating", required = false) Float max_rating) {
        return ResponseEntity.ok(service.findAll(pid, min_rating, max_rating).stream().map(mapper::toDto).collect(Collectors.toList()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
