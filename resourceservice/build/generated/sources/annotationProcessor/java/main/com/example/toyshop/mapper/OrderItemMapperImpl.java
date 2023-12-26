package com.example.toyshop.mapper;

import com.example.toyshop.dto.order.OrderItemCreateDTO;
import com.example.toyshop.dto.order.OrderItemListDTO;
import com.example.toyshop.dto.product.ProductCategoryDTO;
import com.example.toyshop.dto.product.ProductImageDTO;
import com.example.toyshop.entity.OrderItem;
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
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toEntity(OrderItemCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.quantity( dto.getQuantity() );

        return orderItem.build();
    }

    @Override
    public OrderItemCreateDTO toDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemCreateDTO orderItemCreateDTO = new OrderItemCreateDTO();

        orderItemCreateDTO.setQuantity( orderItem.getQuantity() );

        return orderItemCreateDTO;
    }

    @Override
    public OrderItemListDTO toListDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemListDTO orderItemListDTO = new OrderItemListDTO();

        orderItemListDTO.setId( orderItem.getId() );
        orderItemListDTO.setQuantity( orderItem.getQuantity() );
        orderItemListDTO.setProduct( productToProductCategoryDTO( orderItem.getProduct() ) );

        return orderItemListDTO;
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
