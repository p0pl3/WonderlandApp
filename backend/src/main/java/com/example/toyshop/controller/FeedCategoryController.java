package com.example.toyshop.controller;

import com.example.toyshop.dto.feed_category.FeedCategoryCreateDTO;
import com.example.toyshop.dto.feed_category.FeedCategoryListDTO;
import com.example.toyshop.mapper.FeedCategoryMapper;
import com.example.toyshop.service.FeedCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feed_category")
@AllArgsConstructor
public class FeedCategoryController {
    private final FeedCategoryService service;
    private final FeedCategoryMapper mapper;

    @PostMapping("/")
    public ResponseEntity<FeedCategoryListDTO> create(@RequestBody FeedCategoryCreateDTO category) {
        return ResponseEntity.ok(mapper.toListDto(service.create(mapper.fromCreateDto(category))));
    }

    @GetMapping("/list")
    public ResponseEntity<List<FeedCategoryListDTO>> getFeedCategoryList() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toListDto).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
