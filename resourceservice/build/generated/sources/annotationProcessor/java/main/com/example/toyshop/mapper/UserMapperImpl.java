package com.example.toyshop.mapper;

import com.example.toyshop.dto.order.OrderCreateDTO;
import com.example.toyshop.dto.order.OrderItemCreateDTO;
import com.example.toyshop.dto.product_comment.ProductCommentDTO;
import com.example.toyshop.dto.user.UserCreateDTO;
import com.example.toyshop.dto.user.UserDTO;
import com.example.toyshop.dto.user.UserDetailDTO;
import com.example.toyshop.entity.Order;
import com.example.toyshop.entity.OrderItem;
import com.example.toyshop.entity.ProductComment;
import com.example.toyshop.entity.User;
import com.example.toyshop.security.Role;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromDetailDto(UserDetailDTO userDetailDTO) {
        if ( userDetailDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDetailDTO.getId() );
        user.email( userDetailDTO.getEmail() );
        user.orders( orderCreateDTOListToOrderList( userDetailDTO.getOrders() ) );
        user.productComments( productCommentDTOListToProductCommentList( userDetailDTO.getProductComments() ) );
        user.role( userDetailDTO.getRole() );

        return user.build();
    }

    @Override
    public UserDetailDTO toDetailDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDetailDTO userDetailDTO = new UserDetailDTO();

        userDetailDTO.setId( user.getId() );
        userDetailDTO.setEmail( user.getEmail() );
        userDetailDTO.setRole( user.getRole() );
        userDetailDTO.setOrders( orderListToOrderCreateDTOList( user.getOrders() ) );
        userDetailDTO.setProductComments( productCommentListToProductCommentDTOList( user.getProductComments() ) );

        return userDetailDTO;
    }

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setRole( user.getRole() );

        return userDTO;
    }

    @Override
    public User fromDto(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( dto.getId() );
        user.email( dto.getEmail() );
        user.role( dto.getRole() );

        return user.build();
    }

    @Override
    public User fromCreateDto(UserCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( dto.getEmail() );
        user.password( dto.getPassword() );
        if ( dto.getRole() != null ) {
            user.role( Enum.valueOf( Role.class, dto.getRole() ) );
        }

        return user.build();
    }

    @Override
    public User update(UserCreateDTO userDetailDto, User user) {
        if ( userDetailDto == null ) {
            return user;
        }

        if ( userDetailDto.getEmail() != null ) {
            user.setEmail( userDetailDto.getEmail() );
        }
        if ( userDetailDto.getPassword() != null ) {
            user.setPassword( userDetailDto.getPassword() );
        }
        if ( userDetailDto.getRole() != null ) {
            user.setRole( Enum.valueOf( Role.class, userDetailDto.getRole() ) );
        }

        return user;
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

    protected Order orderCreateDTOToOrder(OrderCreateDTO orderCreateDTO) {
        if ( orderCreateDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.address( orderCreateDTO.getAddress() );
        order.phone( orderCreateDTO.getPhone() );
        order.status( orderCreateDTO.getStatus() );
        order.total( orderCreateDTO.getTotal() );
        order.orderItems( orderItemCreateDTOListToOrderItemList( orderCreateDTO.getOrderItems() ) );

        return order.build();
    }

    protected List<Order> orderCreateDTOListToOrderList(List<OrderCreateDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Order> list1 = new ArrayList<Order>( list.size() );
        for ( OrderCreateDTO orderCreateDTO : list ) {
            list1.add( orderCreateDTOToOrder( orderCreateDTO ) );
        }

        return list1;
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
        productComment.author( fromDto( productCommentDTO.getAuthor() ) );

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

    protected OrderCreateDTO orderToOrderCreateDTO(Order order) {
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

    protected List<OrderCreateDTO> orderListToOrderCreateDTOList(List<Order> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderCreateDTO> list1 = new ArrayList<OrderCreateDTO>( list.size() );
        for ( Order order : list ) {
            list1.add( orderToOrderCreateDTO( order ) );
        }

        return list1;
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
        productCommentDTO.setAuthor( toDto( productComment.getAuthor() ) );

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
