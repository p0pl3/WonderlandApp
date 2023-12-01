package com.example.toyshop.service;


import com.example.toyshop.entity.FeedCategory;
import com.example.toyshop.repository.FeedCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedCategoryService {

    private final FeedCategoryRepository repository;

    public FeedCategory create(FeedCategory dto) {
        return repository.save(dto);
    }

    public List<FeedCategory> findAll() {
        return repository.findAll();
    }

    public FeedCategory findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
