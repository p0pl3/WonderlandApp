package com.example.toyshop.mapper;

import com.example.toyshop.dto.product.ProductImageDTO;
import com.example.toyshop.entity.ProductImage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-27T00:19:34+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.9 (GraalVM Community)"
)
@Component
public class ProductImageMapperImpl implements ProductImageMapper {

    @Override
    public ProductImageDTO toDto(ProductImage image) {
        if ( image == null ) {
            return null;
        }

        ProductImageDTO productImageDTO = new ProductImageDTO();

        productImageDTO.setId( image.getId() );
        productImageDTO.setUrl( image.getUrl() );

        return productImageDTO;
    }

    @Override
    public ProductImage toEntity(ProductImageDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductImage.ProductImageBuilder productImage = ProductImage.builder();

        productImage.id( dto.getId() );
        productImage.url( dto.getUrl() );

        return productImage.build();
    }
}
