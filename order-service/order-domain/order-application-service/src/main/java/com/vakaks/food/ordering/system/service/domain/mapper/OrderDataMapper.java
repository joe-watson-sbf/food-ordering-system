package com.vakaks.food.ordering.system.service.domain.mapper;

import com.vakaks.food.ordering.system.domain.valueobject.CustomerId;
import com.vakaks.food.ordering.system.domain.valueobject.Money;
import com.vakaks.food.ordering.system.domain.valueobject.ProductId;
import com.vakaks.food.ordering.system.domain.valueobject.RestaurantId;
import com.vakaks.food.ordering.system.entity.Order;
import com.vakaks.food.ordering.system.entity.OrderItem;
import com.vakaks.food.ordering.system.entity.Product;
import com.vakaks.food.ordering.system.entity.Restaurant;
import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.vakaks.food.ordering.system.service.domain.dto.create.OrderAddress;
import com.vakaks.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import com.vakaks.food.ordering.system.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Component
public class OrderDataMapper {


    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand){
        return Restaurant.Builder
                .builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                    new Product(new ProductId(orderItem.getProductId())))
                        .toList()
                )
                .build();
    }


    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand){
        return Order.Builder
                .builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemsEntities(createOrderCommand.getItems()))
                .build();
    }


    public CreateOrderResponse orderToCreateOrderResponse(Order order){
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTranckingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }



    public TrackOrderResponse orderToTrackOrderResponse(Order order){
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTranckingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }


    private List<OrderItem> orderItemsToOrderItemsEntities(
            List<com.vakaks.food.ordering.system.service.domain.dto.create.OrderItem> orderItems) {

        return orderItems.stream()
                .map(orderItem -> OrderItem.Builder
                        .builder()
                        .product(new Product(new ProductId(orderItem.getProductId())))
                        .price(new Money(orderItem.getPrice()))
                        .quantity(orderItem.getQuantity())
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()).toList();
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }

}
