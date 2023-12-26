package com.example.toyshop.mapper;

import com.example.toyshop.dto.feed.FeedDTO;
import com.example.toyshop.dto.feed_comment.FeedCommentCreateDTO;
import com.example.toyshop.dto.feed_comment.FeedCommentDetailDTO;
import com.example.toyshop.dto.feed_comment.FeedCommentListDTO;
import com.example.toyshop.dto.user.UserDTO;
import com.example.toyshop.entity.Feed;
import com.example.toyshop.entity.FeedComment;
import com.example.toyshop.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-27T00:19:34+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.9 (GraalVM Community)"
)
@Component
public class FeedCommentMapperImpl implements FeedCommentMapper {

    @Override
    public FeedComment fromCreateDto(FeedCommentCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FeedComment.FeedCommentBuilder feedComment = FeedComment.builder();

        feedComment.comment( dto.getComment() );

        return feedComment.build();
    }

    @Override
    public FeedCommentDetailDTO toDetailDto(FeedComment dto) {
        if ( dto == null ) {
            return null;
        }

        FeedCommentDetailDTO feedCommentDetailDTO = new FeedCommentDetailDTO();

        feedCommentDetailDTO.setId( dto.getId() );
        feedCommentDetailDTO.setComment( dto.getComment() );
        feedCommentDetailDTO.setAuthor( userToUserDTO( dto.getAuthor() ) );
        feedCommentDetailDTO.setFeed( feedToFeedDTO( dto.getFeed() ) );

        return feedCommentDetailDTO;
    }

    @Override
    public FeedCommentListDTO toListDto(FeedComment dto) {
        if ( dto == null ) {
            return null;
        }

        FeedCommentListDTO feedCommentListDTO = new FeedCommentListDTO();

        feedCommentListDTO.setId( dto.getId() );
        feedCommentListDTO.setComment( dto.getComment() );
        feedCommentListDTO.setDateCreation( dto.getDateCreation() );
        feedCommentListDTO.setAuthor( userToUserDTO( dto.getAuthor() ) );

        return feedCommentListDTO;
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setRole( user.getRole() );

        return userDTO;
    }

    protected FeedDTO feedToFeedDTO(Feed feed) {
        if ( feed == null ) {
            return null;
        }

        FeedDTO feedDTO = new FeedDTO();

        feedDTO.setId( feed.getId() );
        feedDTO.setTitle( feed.getTitle() );

        return feedDTO;
    }
}
