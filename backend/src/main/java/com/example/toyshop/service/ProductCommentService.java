package com.example.toyshop.service;

import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductComment;
import com.example.toyshop.entity.User;
import com.example.toyshop.repository.product_comment.ProductCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductCommentService {
    private final ProductCommentRepository repository;
    private final ProductService productService;

    public ProductComment create(ProductComment comment, Long productId, User user) {
        LocalDate now = LocalDate.now();
        Product product = productService.findById(productId);
        comment.setProduct(product);
        comment.setAuthor(user);
        comment.setDateCreation(now);
        return repository.save(comment);
    }

    public List<ProductComment> findAll(Long productId, Float min_rating, Float max_rating) {
        return repository.findByFilters(productId, min_rating, max_rating);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
