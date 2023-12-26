package com.example.toyshop.mapper;

import com.example.toyshop.dto.feed.FeedCreateDTO;
import com.example.toyshop.dto.feed.FeedListDTO;
import com.example.toyshop.dto.feed_category.FeedCategoryDTO;
import com.example.toyshop.dto.feed_comment.FeedCommentListDTO;
import com.example.toyshop.dto.user.UserDTO;
import com.example.toyshop.entity.Feed;
import com.example.toyshop.entity.FeedCategory;
import com.example.toyshop.entity.FeedComment;
import com.example.toyshop.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-27T00:19:34+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.9 (GraalVM Community)"
)
@Component
public class FeedMapperImpl implements FeedMapper {

    @Override
    public Feed fromCreateDto(FeedCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Feed.FeedBuilder feed = Feed.builder();

        feed.title( dto.getTitle() );
        feed.description( dto.getDescription() );

        return feed.build();
    }

    @Override
    public FeedListDTO toListDto(Feed feed) {
        if ( feed == null ) {
            return null;
        }

        FeedListDTO feedListDTO = new FeedListDTO();

        feedListDTO.setId( feed.getId() );
        feedListDTO.setTitle( feed.getTitle() );
        feedListDTO.setDescription( feed.getDescription() );
        feedListDTO.setDateCreate( feed.getDateCreate() );
        feedListDTO.setAuthor( userToUserDTO( feed.getAuthor() ) );
        feedListDTO.setCategory( feedCategoryToFeedCategoryDTO( feed.getCategory() ) );
        feedListDTO.setFeedComments( feedCommentListToFeedCommentListDTOList( feed.getFeedComments() ) );

        return feedListDTO;
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

    protected FeedCategoryDTO feedCategoryToFeedCategoryDTO(FeedCategory feedCategory) {
        if ( feedCategory == null ) {
            return null;
        }

        FeedCategoryDTO feedCategoryDTO = new FeedCategoryDTO();

        feedCategoryDTO.setId( feedCategory.getId() );
        feedCategoryDTO.setTitle( feedCategory.getTitle() );

        return feedCategoryDTO;
    }

    protected FeedCommentListDTO feedCommentToFeedCommentListDTO(FeedComment feedComment) {
        if ( feedComment == null ) {
            return null;
        }

        FeedCommentListDTO feedCommentListDTO = new FeedCommentListDTO();

        feedCommentListDTO.setId( feedComment.getId() );
        feedCommentListDTO.setComment( feedComment.getComment() );
        feedCommentListDTO.setDateCreation( feedComment.getDateCreation() );
        feedCommentListDTO.setAuthor( userToUserDTO( feedComment.getAuthor() ) );

        return feedCommentListDTO;
    }

    protected List<FeedCommentListDTO> feedCommentListToFeedCommentListDTOList(List<FeedComment> list) {
        if ( list == null ) {
            return null;
        }

        List<FeedCommentListDTO> list1 = new ArrayList<FeedCommentListDTO>( list.size() );
        for ( FeedComment feedComment : list ) {
            list1.add( feedCommentToFeedCommentListDTO( feedComment ) );
        }

        return list1;
    }
}
