package com.example.toyshop.mapper;

import com.example.toyshop.dto.product.ProductCategoryDTO;
import com.example.toyshop.dto.product.ProductImageDTO;
import com.example.toyshop.dto.product_comment.ProductCommentCreateDTO;
import com.example.toyshop.dto.product_comment.ProductCommentDTO;
import com.example.toyshop.dto.product_comment.ProductCommentDetailDTO;
import com.example.toyshop.dto.user.UserDTO;
import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductComment;
import com.example.toyshop.entity.ProductImage;
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
public class ProductCommentMapperImpl implements ProductCommentMapper {

    @Override
    public ProductComment toEntity(ProductCommentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductComment.ProductCommentBuilder productComment = ProductComment.builder();

        productComment.id( dto.getId() );
        productComment.comment( dto.getComment() );
        productComment.rating( dto.getRating() );
        productComment.dateCreation( dto.getDateCreation() );
        productComment.author( userDTOToUser( dto.getAuthor() ) );

        return productComment.build();
    }

    @Override
    public ProductCommentDTO toDto(ProductComment comment) {
        if ( comment == null ) {
            return null;
        }

        ProductCommentDTO productCommentDTO = new ProductCommentDTO();

        productCommentDTO.setId( comment.getId() );
        productCommentDTO.setComment( comment.getComment() );
        productCommentDTO.setRating( comment.getRating() );
        productCommentDTO.setDateCreation( comment.getDateCreation() );
        productCommentDTO.setAuthor( userToUserDTO( comment.getAuthor() ) );

        return productCommentDTO;
    }

    @Override
    public ProductComment fromCreateDto(ProductCommentCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductComment.ProductCommentBuilder productComment = ProductComment.builder();

        productComment.comment( dto.getComment() );
        productComment.rating( dto.getRating() );

        return productComment.build();
    }

    @Override
    public ProductCommentDetailDTO toDetailDto(ProductComment comment) {
        if ( comment == null ) {
            return null;
        }

        ProductCommentDetailDTO productCommentDetailDTO = new ProductCommentDetailDTO();

        productCommentDetailDTO.setId( comment.getId() );
        productCommentDetailDTO.setComment( comment.getComment() );
        productCommentDetailDTO.setRating( comment.getRating() );
        productCommentDetailDTO.setAuthor( userToUserDTO( comment.getAuthor() ) );
        productCommentDetailDTO.setProduct( productToProductCategoryDTO( comment.getProduct() ) );

        return productCommentDetailDTO;
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDTO.getId() );
        user.email( userDTO.getEmail() );
        user.role( userDTO.getRole() );

        return user.build();
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

    protected ProductImageDTO productImageToProductImageDTO(ProductImage productImage) {
        if ( productImage == null ) {
            return null;
        }

        ProductImageDTO productImageDTO = new ProductImageDTO();

        productImageDTO.setId( productImage.getId() );
        productImageDTO.setUrl( productImage.getUrl() );

        return productImageDTO;
    }

    protected List<ProductImageDTO> productImageListToProductImageDTOList(List<ProductImage> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductImageDTO> list1 = new ArrayList<ProductImageDTO>( list.size() );
        for ( ProductImage productImage : list ) {
            list1.add( productImageToProductImageDTO( productImage ) );
        }

        return list1;
    }

    protected ProductCategoryDTO productToProductCategoryDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();

        productCategoryDTO.setId( product.getId() );
        productCategoryDTO.setTitle( product.getTitle() );
        productCategoryDTO.setPrice( product.getPrice() );
        productCategoryDTO.setNew_price( product.getNew_price() );
        productCategoryDTO.setImages( productImageListToProductImageDTOList( product.getImages() ) );

        return productCategoryDTO;
    }
}
