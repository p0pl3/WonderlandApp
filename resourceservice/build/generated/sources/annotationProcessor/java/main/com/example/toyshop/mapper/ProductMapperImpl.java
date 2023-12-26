package com.example.toyshop.mapper;

import com.example.toyshop.dto.category.CategoryProductDTO;
import com.example.toyshop.dto.product.ProductCategoryDTO;
import com.example.toyshop.dto.product.ProductCreateDTO;
import com.example.toyshop.dto.product.ProductDetailDTO;
import com.example.toyshop.dto.product.ProductImageDTO;
import com.example.toyshop.dto.product.ProductListDTO;
import com.example.toyshop.dto.product_comment.ProductCommentDTO;
import com.example.toyshop.dto.user.UserDTO;
import com.example.toyshop.entity.Category;
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
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntityFromDetailDto(ProductDetailDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( dto.getId() );
        product.title( dto.getTitle() );
        product.description( dto.getDescription() );
        product.price( dto.getPrice() );
        product.discount( dto.getDiscount() );
        product.new_price( dto.getNew_price() );
        product.amount( dto.getAmount() );
        product.rating( dto.getRating() );
        product.delivery_period( dto.getDelivery_period() );
        product.category( categoryProductDTOToCategory( dto.getCategory() ) );
        product.images( productImageDTOListToProductImageList( dto.getImages() ) );
        product.comments( productCommentDTOListToProductCommentList( dto.getComments() ) );

        return product.build();
    }

    @Override
    public ProductDetailDTO toDetailDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDetailDTO productDetailDTO = new ProductDetailDTO();

        productDetailDTO.setId( product.getId() );
        productDetailDTO.setTitle( product.getTitle() );
        productDetailDTO.setPrice( product.getPrice() );
        productDetailDTO.setAmount( product.getAmount() );
        productDetailDTO.setDescription( product.getDescription() );
        productDetailDTO.setRating( product.getRating() );
        productDetailDTO.setCategory( categoryToCategoryProductDTO( product.getCategory() ) );
        productDetailDTO.setImages( productImageListToProductImageDTOList( product.getImages() ) );
        productDetailDTO.setComments( productCommentListToProductCommentDTOList( product.getComments() ) );
        productDetailDTO.setNew_price( product.getNew_price() );
        productDetailDTO.setDiscount( product.getDiscount() );
        productDetailDTO.setDelivery_period( product.getDelivery_period() );

        return productDetailDTO;
    }

    @Override
    public ProductCategoryDTO toCategoryDto(Product product) {
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

    @Override
    public ProductListDTO toListDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductListDTO productListDTO = new ProductListDTO();

        productListDTO.setId( product.getId() );
        productListDTO.setTitle( product.getTitle() );
        productListDTO.setPrice( product.getPrice() );
        productListDTO.setRating( product.getRating() );
        productListDTO.setCategory( categoryToCategoryProductDTO( product.getCategory() ) );
        productListDTO.setImages( productImageListToProductImageDTOList( product.getImages() ) );
        productListDTO.setNew_price( product.getNew_price() );
        productListDTO.setDiscount( product.getDiscount() );
        productListDTO.setDelivery_period( product.getDelivery_period() );

        return productListDTO;
    }

    @Override
    public Product fromCreateDto(ProductCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.title( dto.getTitle() );
        product.description( dto.getDescription() );
        product.price( dto.getPrice() );
        product.discount( dto.getDiscount() );
        product.amount( dto.getAmount() );
        product.rating( dto.getRating() );
        product.delivery_period( dto.getDelivery_period() );

        return product.build();
    }

    @Override
    public Product update(ProductCreateDTO dto, Product product) {
        if ( dto == null ) {
            return product;
        }

        if ( dto.getTitle() != null ) {
            product.setTitle( dto.getTitle() );
        }
        if ( dto.getDescription() != null ) {
            product.setDescription( dto.getDescription() );
        }
        if ( dto.getPrice() != null ) {
            product.setPrice( dto.getPrice() );
        }
        if ( dto.getDiscount() != null ) {
            product.setDiscount( dto.getDiscount() );
        }
        if ( dto.getAmount() != null ) {
            product.setAmount( dto.getAmount() );
        }
        if ( dto.getRating() != null ) {
            product.setRating( dto.getRating() );
        }
        if ( dto.getDelivery_period() != null ) {
            product.setDelivery_period( dto.getDelivery_period() );
        }

        return product;
    }

    protected Category categoryProductDTOToCategory(CategoryProductDTO categoryProductDTO) {
        if ( categoryProductDTO == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( categoryProductDTO.getId() );
        category.title( categoryProductDTO.getTitle() );
        category.slug( categoryProductDTO.getSlug() );

        return category.build();
    }

    protected ProductImage productImageDTOToProductImage(ProductImageDTO productImageDTO) {
        if ( productImageDTO == null ) {
            return null;
        }

        ProductImage.ProductImageBuilder productImage = ProductImage.builder();

        productImage.id( productImageDTO.getId() );
        productImage.url( productImageDTO.getUrl() );

        return productImage.build();
    }

    protected List<ProductImage> productImageDTOListToProductImageList(List<ProductImageDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductImage> list1 = new ArrayList<ProductImage>( list.size() );
        for ( ProductImageDTO productImageDTO : list ) {
            list1.add( productImageDTOToProductImage( productImageDTO ) );
        }

        return list1;
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

    protected ProductComment productCommentDTOToProductComment(ProductCommentDTO productCommentDTO) {
        if ( productCommentDTO == null ) {
            return null;
        }

        ProductComment.ProductCommentBuilder productComment = ProductComment.builder();

        productComment.id( productCommentDTO.getId() );
        productComment.comment( productCommentDTO.getComment() );
        productComment.rating( productCommentDTO.getRating() );
        productComment.dateCreation( productCommentDTO.getDateCreation() );
        productComment.author( userDTOToUser( productCommentDTO.getAuthor() ) );

        return productComment.build();
    }

    protected List<ProductComment> productCommentDTOListToProductCommentList(List<ProductCommentDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductComment> list1 = new ArrayList<ProductComment>( list.size() );
        for ( ProductCommentDTO productCommentDTO : list ) {
            list1.add( productCommentDTOToProductComment( productCommentDTO ) );
        }

        return list1;
    }

    protected CategoryProductDTO categoryToCategoryProductDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryProductDTO categoryProductDTO = new CategoryProductDTO();

        categoryProductDTO.setId( category.getId() );
        categoryProductDTO.setTitle( category.getTitle() );
        categoryProductDTO.setSlug( category.getSlug() );

        return categoryProductDTO;
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

    protected ProductCommentDTO productCommentToProductCommentDTO(ProductComment productComment) {
        if ( productComment == null ) {
            return null;
        }

        ProductCommentDTO productCommentDTO = new ProductCommentDTO();

        productCommentDTO.setId( productComment.getId() );
        productCommentDTO.setComment( productComment.getComment() );
        productCommentDTO.setRating( productComment.getRating() );
        productCommentDTO.setDateCreation( productComment.getDateCreation() );
        productCommentDTO.setAuthor( userToUserDTO( productComment.getAuthor() ) );

        return productCommentDTO;
    }

    protected List<ProductCommentDTO> productCommentListToProductCommentDTOList(List<ProductComment> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductCommentDTO> list1 = new ArrayList<ProductCommentDTO>( list.size() );
        for ( ProductComment productComment : list ) {
            list1.add( productCommentToProductCommentDTO( productComment ) );
        }

        return list1;
    }
}
