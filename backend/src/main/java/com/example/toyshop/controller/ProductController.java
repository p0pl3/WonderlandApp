package com.example.toyshop.controller;

import com.example.toyshop.dto.product.ProductCreateDTO;
import com.example.toyshop.dto.product.ProductDetailDTO;
import com.example.toyshop.dto.product.ProductListDTO;
import com.example.toyshop.mapper.ProductMapper;
import com.example.toyshop.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ProductDetailDTO> create(@RequestPart("files") MultipartFile[] files,
                                                   @RequestPart("product") ProductCreateDTO dto) throws IOException {
        return ResponseEntity.ok(mapper.toDetailDto(service.create(mapper.fromCreateDto(dto), dto.getCategoryId(), files)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDetailDto(service.findById(id)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductListDTO>> getProductList(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "category", required = false) Long category,
            @RequestParam(name = "min_price", required = false) Float min_price,
            @RequestParam(name = "max_price", required = false) Float max_price,
            @RequestParam(name = "delivery_period", required = false) Short delivery_period,
            @RequestParam(name = "discount", required = false) Float discount,
            @RequestParam(name = "min_rating", required = false) Float min_rating,
            @RequestParam(name = "max_rating", required = false) Float max_rating) {
        return ResponseEntity.ok(service.findAll(title, category, min_price, max_price, delivery_period, discount,
                min_rating, max_rating).stream().map(mapper::toListDto).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> update(@PathVariable Long id, @RequestBody ProductCreateDTO dto) {
        return ResponseEntity.ok(mapper.toDetailDto(service.update(mapper.update(dto, service.findById(id)))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
