package com.example.toyshop.service;

import com.example.toyshop.entity.Feed;
import com.example.toyshop.entity.User;
import com.example.toyshop.repository.feed.FeedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedService {
    private final FeedRepository repository;
    private final FeedCategoryService feedCategoryService;


    public Feed create(String title, Long categoryId, String description, User user) {
        Feed feed = new Feed();
        feed.setTitle(title);
        feed.setCategory(feedCategoryService.findById(categoryId));
        feed.setAuthor(user);
        feed.setDescription(description);
        feed.setDateCreate(LocalDate.now());
        return repository.save(feed);
    }

    public List<Feed> findAll(String title, Long category) {
        return repository.findByFilters(title, category);
    }

    public List<Feed> findAllByUserId(Long id) {
        return repository.findAllByAuthorId(id);
    }

    public Feed findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Feed findByIdEntity(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Feed update(Feed feed) {
        return repository.save(feed);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
