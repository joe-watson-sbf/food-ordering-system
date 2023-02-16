package com.vakaks.food.ordering.system.service.domain;

import com.vakaks.food.ordering.system.OrderDomainService;
import com.vakaks.food.ordering.system.entity.Customer;
import com.vakaks.food.ordering.system.entity.Order;
import com.vakaks.food.ordering.system.entity.Restaurant;
import com.vakaks.food.ordering.system.event.OrderCreatedEvent;
import com.vakaks.food.ordering.system.exception.OrderDomainException;
import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.vakaks.food.ordering.system.service.domain.mapper.OrderDataMapper;
import com.vakaks.food.ordering.system.service.domain.port.output.repository.CustomerRepository;
import com.vakaks.food.ordering.system.service.domain.port.output.repository.OrderRepository;
import com.vakaks.food.ordering.system.service.domain.port.output.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

    public OrderCreateCommandHandler(OrderDomainService orderDomainService,
                                     OrderRepository orderRepository,
                                     CustomerRepository customerRepository,
                                     RestaurantRepository restaurantRepository,
                                     OrderDataMapper orderDataMapper, ApplicationDomainEventPublisher applicationDomainEventPublisher) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
        this.applicationDomainEventPublisher = applicationDomainEventPublisher;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        Order orderResult = saveOrder(order);
        applicationDomainEventPublisher.publish(orderCreatedEvent);
        log.warn("Order is created with id : {}", orderResult.getId().getValue());
        return orderDataMapper.orderToCreateOrderResponse(orderResult);
    }




    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);

        if (optionalRestaurant.isEmpty()){
            String message = "Could not find restaurant with restaurant id : " + createOrderCommand.getRestaurantId();
            log.warn(message);
            throw new OrderDomainException(message);
        }

        return optionalRestaurant.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()){
            String message = "Could not find customer id : " + customerId;
            log.warn(message);
            throw new OrderDomainException(message);
        }
    }

    private Order saveOrder(Order order){
        Order orderResult = orderRepository.save(order);
        if(orderResult==null){
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }

        log.info("Order is saved with is: {}", orderResult.getId().getValue());
        return orderResult;
    }


}
