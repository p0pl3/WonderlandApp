package com.example.toyshop.service;


import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductImage;
import com.example.toyshop.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository repository;

    @Value("${media.url}")
    private String baseMediaUrl;
    private final String IMAGES_DIRECTORY = baseMediaUrl + "/images/";

    public ProductImage create(MultipartFile file, Product product) throws IOException {
        String fileName = product
                .getId()
                .toString()
                + "_" +
                RandomStringUtils.random(20, true, true)
                + "." +
                file.getContentType().substring(6);
        Path fileNameAndPath = Paths.get(IMAGES_DIRECTORY, fileName);
        Files.write(fileNameAndPath, file.getBytes());
        ProductImage image = new ProductImage();
        image.setProduct(product);
        image.setUrl(fileName);
        return repository.save(image);
    }

    public List<ProductImage> findAllByProductId(Long id) {
        return repository.findAllByProductId(id);
    }


    public void delete(Long id) throws IOException {
        Path fileNameAndPath = Paths.get(IMAGES_DIRECTORY, Objects.requireNonNull(repository.findById(id).orElse(null)).getUrl());
        Files.delete(fileNameAndPath);
        repository.deleteById(id);
    }
}
