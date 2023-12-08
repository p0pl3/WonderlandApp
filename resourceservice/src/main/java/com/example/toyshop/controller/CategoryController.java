package com.example.toyshop.controller;

import com.example.toyshop.dto.category.CategoryCreateDTO;
import com.example.toyshop.dto.category.CategoryListDTO;
import com.example.toyshop.mapper.CategoryMapper;
import com.example.toyshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @PostMapping("/")
    public ResponseEntity<CategoryListDTO> create(@RequestBody CategoryCreateDTO category){
        return ResponseEntity.ok(mapper.toListDto(service.create(mapper.toEntity(category))));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryListDTO>> getCategoryList() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toListDto).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

