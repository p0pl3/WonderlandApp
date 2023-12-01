package com.example.toyshop.controller;

import com.example.toyshop.dto.product.ProductImageDTO;
import com.example.toyshop.mapper.ProductImageMapper;
import com.example.toyshop.service.ProductImageService;
import com.example.toyshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product/images")
@AllArgsConstructor
public class ProductImageController {
    private final ProductService service;
    private final ProductImageService imageService;
    private ProductImageMapper mapper;

    @PostMapping("/{pid}")
    public ResponseEntity<ProductImageDTO> addImage(@RequestPart("file") MultipartFile file,
                                                    @PathVariable Long pid) throws IOException {
        return ResponseEntity.ok(mapper.toDto(service.addImage(pid, file)));
    }

    @GetMapping("/{pid}/list")
    public ResponseEntity<List<ProductImageDTO>> getImages(@PathVariable Long pid) {
        return ResponseEntity.ok(imageService.findAllByProductId(pid).stream().map(mapper::toDto).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) throws IOException {
        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
