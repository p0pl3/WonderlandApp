package com.example.toyshop.service;

import com.example.toyshop.entity.Category;
import com.example.toyshop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;


    public Category create(Category category) {
        return repository.save(category);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public List<Category> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Category findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Category update(Category Category) {
        return repository.save(Category);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
