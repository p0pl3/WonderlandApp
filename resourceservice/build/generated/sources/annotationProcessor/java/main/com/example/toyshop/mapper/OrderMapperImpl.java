package com.example.toyshop.mapper;

import com.example.toyshop.dto.order.OrderCreateDTO;
import com.example.toyshop.dto.order.OrderItemCreateDTO;
import com.example.toyshop.dto.order.OrderItemListDTO;
import com.example.toyshop.dto.order.OrderListDTO;
import com.example.toyshop.dto.product.ProductCategoryDTO;
import com.example.toyshop.dto.product.ProductImageDTO;
import com.example.toyshop.dto.user.UserDTO;
import com.example.toyshop.entity.Order;
import com.example.toyshop.entity.OrderItem;
import com.example.toyshop.entity.Product;
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
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order fromCreateDto(OrderCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.address( dto.getAddress() );
        order.phone( dto.getPhone() );
        order.status( dto.getStatus() );
        order.total( dto.getTotal() );
        order.orderItems( orderItemCreateDTOListToOrderItemList( dto.getOrderItems() ) );

        return order.build();
    }

    @Override
    public OrderCreateDTO toCreateDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();

        orderCreateDTO.setAddress( order.getAddress() );
        orderCreateDTO.setPhone( order.getPhone() );
        orderCreateDTO.setOrderItems( orderItemListToOrderItemCreateDTOList( order.getOrderItems() ) );
        orderCreateDTO.setStatus( order.getStatus() );
        orderCreateDTO.setTotal( order.getTotal() );

        return orderCreateDTO;
    }

    @Override
    public Order fromListDto(OrderListDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( dto.getId() );
        order.address( dto.getAddress() );
        order.creationDate( dto.getCreationDate() );
        order.phone( dto.getPhone() );
        order.status( dto.getStatus() );
        order.total( dto.getTotal() );
        order.buyer( userDTOToUser( dto.getBuyer() ) );
        order.orderItems( orderItemListDTOListToOrderItemList( dto.getOrderItems() ) );

        return order.build();
    }

    @Override
    public OrderListDTO toListDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderListDTO orderListDTO = new OrderListDTO();

        orderListDTO.setId( order.getId() );
        orderListDTO.setAddress( order.getAddress() );
        orderListDTO.setCreationDate( order.getCreationDate() );
        orderListDTO.setPhone( order.getPhone() );
        orderListDTO.setBuyer( userToUserDTO( order.getBuyer() ) );
        orderListDTO.setOrderItems( orderItemListToOrderItemListDTOList( order.getOrderItems() ) );
        orderListDTO.setStatus( order.getStatus() );
        orderListDTO.setTotal( order.getTotal() );

        return orderListDTO;
    }

    protected OrderItem orderItemCreateDTOToOrderItem(OrderItemCreateDTO orderItemCreateDTO) {
        if ( orderItemCreateDTO == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.quantity( orderItemCreateDTO.getQuantity() );

        return orderItem.build();
    }

    protected List<OrderItem> orderItemCreateDTOListToOrderItemList(List<OrderItemCreateDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemCreateDTO orderItemCreateDTO : list ) {
            list1.add( orderItemCreateDTOToOrderItem( orderItemCreateDTO ) );
        }

        return list1;
    }

    protected OrderItemCreateDTO orderItemToOrderItemCreateDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemCreateDTO orderItemCreateDTO = new OrderItemCreateDTO();

        orderItemCreateDTO.setQuantity( orderItem.getQuantity() );

        return orderItemCreateDTO;
    }

    protected List<OrderItemCreateDTO> orderItemListToOrderItemCreateDTOList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemCreateDTO> list1 = new ArrayList<OrderItemCreateDTO>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItemCreateDTO( orderItem ) );
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

    protected Product productCategoryDTOToProduct(ProductCategoryDTO productCategoryDTO) {
        if ( productCategoryDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productCategoryDTO.getId() );
        product.title( productCategoryDTO.getTitle() );
        product.price( productCategoryDTO.getPrice() );
        product.new_price( productCategoryDTO.getNew_price() );
        product.images( productImageDTOListToProductImageList( productCategoryDTO.getImages() ) );

        return product.build();
    }

    protected OrderItem orderItemListDTOToOrderItem(OrderItemListDTO orderItemListDTO) {
        if ( orderItemListDTO == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.id( orderItemListDTO.getId() );
        orderItem.quantity( orderItemListDTO.getQuantity() );
        orderItem.product( productCategoryDTOToProduct( orderItemListDTO.getProduct() ) );

        return orderItem.build();
    }

    protected List<OrderItem> orderItemListDTOListToOrderItemList(List<OrderItemListDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemListDTO orderItemListDTO : list ) {
            list1.add( orderItemListDTOToOrderItem( orderItemListDTO ) );
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

    protected OrderItemListDTO orderItemToOrderItemListDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemListDTO orderItemListDTO = new OrderItemListDTO();

        orderItemListDTO.setId( orderItem.getId() );
        orderItemListDTO.setQuantity( orderItem.getQuantity() );
        orderItemListDTO.setProduct( productToProductCategoryDTO( orderItem.getProduct() ) );

        return orderItemListDTO;
    }

    protected List<OrderItemListDTO> orderItemListToOrderItemListDTOList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemListDTO> list1 = new ArrayList<OrderItemListDTO>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItemListDTO( orderItem ) );
        }

        return list1;
    }
}
