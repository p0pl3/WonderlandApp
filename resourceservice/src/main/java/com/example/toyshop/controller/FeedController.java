package com.example.toyshop.controller;

import com.example.toyshop.dto.feed.FeedCreateDTO;
import com.example.toyshop.dto.feed.FeedListDTO;
import com.example.toyshop.entity.User;
import com.example.toyshop.mapper.FeedMapper;
import com.example.toyshop.security.AuthenticatedUserService;
import com.example.toyshop.service.FeedService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feed")
@AllArgsConstructor
public class FeedController {
    private final FeedService service;
    private final AuthenticatedUserService authenticatedUserService;
    private FeedMapper mapper;

    @PostMapping("/")
    public ResponseEntity<FeedListDTO> create(@RequestBody FeedCreateDTO dto) {
        User user = authenticatedUserService.getAuthenticatedPerson();
        return ResponseEntity.ok(mapper.toListDto(service.create(dto.getTitle(), dto.getCategoryId(), dto.getDescription(), user)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<FeedListDTO>> getAllFeeds(@RequestParam(name = "title", required = false) String title,
                                                         @RequestParam(name = "category", required = false) Long category) {
        return ResponseEntity.ok(service.findAll(title, category).stream().map(mapper::toListDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedListDTO> getFeedById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toListDto(service.findById(id)));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<FeedListDTO>> getAllUserFeeds(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAllByUserId(id).stream().map(mapper::toListDto).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
