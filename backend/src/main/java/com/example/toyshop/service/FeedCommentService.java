package com.example.toyshop.service;


import com.example.toyshop.entity.*;
import com.example.toyshop.repository.FeedCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedCommentService {
    private final FeedCommentRepository repository;
    private final FeedService feedService;

    public FeedComment create(FeedComment dto, Long feedId, User user) {
        LocalDate now = LocalDate.now();
        dto.setAuthor(user);
        Feed feed = feedService.findByIdEntity(feedId);
        dto.setFeed(feed);
        dto.setDateCreation(now);
        return repository.save(dto);
    }

    public List<FeedComment> findAllByFeedId(Long id){
        return repository.findAllByFeedId(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
