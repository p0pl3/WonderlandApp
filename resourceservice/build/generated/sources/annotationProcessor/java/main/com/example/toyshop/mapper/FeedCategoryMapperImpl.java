package com.example.toyshop.mapper;

import com.example.toyshop.dto.feed.FeedDTO;
import com.example.toyshop.dto.feed_category.FeedCategoryCreateDTO;
import com.example.toyshop.dto.feed_category.FeedCategoryListDTO;
import com.example.toyshop.entity.Feed;
import com.example.toyshop.entity.FeedCategory;
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
public class FeedCategoryMapperImpl implements FeedCategoryMapper {

    @Override
    public FeedCategory fromCreateDto(FeedCategoryCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FeedCategory.FeedCategoryBuilder feedCategory = FeedCategory.builder();

        feedCategory.title( dto.getTitle() );
        feedCategory.slug( dto.getSlug() );

        return feedCategory.build();
    }

    @Override
    public FeedCategoryCreateDTO toCreateDto(FeedCategory category) {
        if ( category == null ) {
            return null;
        }

        FeedCategoryCreateDTO feedCategoryCreateDTO = new FeedCategoryCreateDTO();

        feedCategoryCreateDTO.setTitle( category.getTitle() );
        feedCategoryCreateDTO.setSlug( category.getSlug() );

        return feedCategoryCreateDTO;
    }

    @Override
    public FeedCategoryListDTO toListDto(FeedCategory category) {
        if ( category == null ) {
            return null;
        }

        FeedCategoryListDTO feedCategoryListDTO = new FeedCategoryListDTO();

        feedCategoryListDTO.setId( category.getId() );
        feedCategoryListDTO.setTitle( category.getTitle() );
        feedCategoryListDTO.setSlug( category.getSlug() );
        feedCategoryListDTO.setFeeds( feedListToFeedDTOList( category.getFeeds() ) );

        return feedCategoryListDTO;
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

    protected List<FeedDTO> feedListToFeedDTOList(List<Feed> list) {
        if ( list == null ) {
            return null;
        }

        List<FeedDTO> list1 = new ArrayList<FeedDTO>( list.size() );
        for ( Feed feed : list ) {
            list1.add( feedToFeedDTO( feed ) );
        }

        return list1;
    }
}
