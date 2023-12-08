package com.example.toyshop.controller;

import com.example.toyshop.dto.feed_comment.FeedCommentCreateDTO;
import com.example.toyshop.dto.feed_comment.FeedCommentDetailDTO;
import com.example.toyshop.dto.feed_comment.FeedCommentListDTO;
import com.example.toyshop.entity.User;
import com.example.toyshop.mapper.FeedCommentMapper;
import com.example.toyshop.security.AuthenticatedUserService;
import com.example.toyshop.service.FeedCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feed/comments")
@AllArgsConstructor
public class FeedCommentController {
    private final FeedCommentService service;
    private final AuthenticatedUserService authenticatedUserService;
    private final FeedCommentMapper mapper;

    @PostMapping("/{fid}")
    public ResponseEntity<FeedCommentDetailDTO> create(@RequestBody FeedCommentCreateDTO dto, @PathVariable Long fid) {
        User user = authenticatedUserService.getAuthenticatedPerson();
        return ResponseEntity.ok(mapper.toDetailDto(service.create(mapper.fromCreateDto(dto), fid, user)));
    }

    @GetMapping("/{fid}/list")
    public ResponseEntity<List<FeedCommentListDTO>> getAllFeedComments(@PathVariable Long fid) {
        return ResponseEntity.ok(service.findAllByFeedId(fid).stream().map(mapper::toListDto).collect(Collectors.toList()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
