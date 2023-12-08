package com.example.toyshop.dto.feed;

import com.example.toyshop.dto.feed_category.FeedCategoryDTO;
import com.example.toyshop.dto.feed_comment.FeedCommentListDTO;
import com.example.toyshop.dto.user.UserDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class FeedListDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate dateCreate;
    private UserDTO author;
    private FeedCategoryDTO category;
    private List<FeedCommentListDTO> feedComments;
}
