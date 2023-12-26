package com.example.toyshop.mapper;

import com.example.toyshop.dto.category.CategoryCreateDTO;
import com.example.toyshop.dto.category.CategoryListDTO;
import com.example.toyshop.dto.product.ProductCategoryDTO;
import com.example.toyshop.dto.product.ProductImageDTO;
import com.example.toyshop.entity.Category;
import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductImage;
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
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryCreateDTO categoryCreateDTO) {
        if ( categoryCreateDTO == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.title( categoryCreateDTO.getTitle() );
        category.slug( categoryCreateDTO.getSlug() );

        return category.build();
    }

    @Override
    public CategoryCreateDTO toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryCreateDTO categoryCreateDTO = new CategoryCreateDTO();

        categoryCreateDTO.setTitle( category.getTitle() );
        categoryCreateDTO.setSlug( category.getSlug() );

        return categoryCreateDTO;
    }

    @Override
    public CategoryListDTO toListDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryListDTO categoryListDTO = new CategoryListDTO();

        categoryListDTO.setId( category.getId() );
        categoryListDTO.setTitle( category.getTitle() );
        categoryListDTO.setSlug( category.getSlug() );
        categoryListDTO.setProducts( productListToProductCategoryDTOList( category.getProducts() ) );

        return categoryListDTO;
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

    protected List<ProductCategoryDTO> productListToProductCategoryDTOList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductCategoryDTO> list1 = new ArrayList<ProductCategoryDTO>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductCategoryDTO( product ) );
        }

        return list1;
    }
}
