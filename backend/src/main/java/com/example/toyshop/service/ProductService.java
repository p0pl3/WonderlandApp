package com.example.toyshop.service;


import com.example.toyshop.entity.Category;
import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductImage;
import com.example.toyshop.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductImageService productImageService;

    public Product create(Product product, Long categoryId, MultipartFile[] files) throws IOException {
        productRepository.save(product);
        if (categoryId != null) {
            Category category = categoryService.findById(categoryId);
            product.setCategory(category);
        }
        if (files != null)
            for (MultipartFile file : files)
                product.addImage(productImageService.create(file, product));
        if (product.getDiscount() != null && product.getDiscount() > 0)
            product.setNew_price(product.getPrice() - product.getPrice() * product.getDiscount() / 100);
        else
            product.setNew_price(product.getPrice());
        return productRepository.save(product);
    }

    public List<Product> findAll(String title, Long categoryId, Float min_price, Float max_price,
                                 Short delivery_period, Float discount, Float min_rating, Float max_rating) {
        return productRepository.
                findByFilters(title, categoryId, min_price, max_price,delivery_period,discount, min_rating, max_rating);
    }
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public ProductImage addImage(Long productId, MultipartFile file) throws IOException {
        try {
            Product product = findById(productId);
            if (file != null) {
                ProductImage image = productImageService.create(file, product);
                product.addImage(image);
                return image;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    public void delete(Long id) {
        findById(id).getImages().forEach(productImage -> {
            try {
                productImageService.delete(productImage.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        productRepository.deleteById(id);
    }
}
