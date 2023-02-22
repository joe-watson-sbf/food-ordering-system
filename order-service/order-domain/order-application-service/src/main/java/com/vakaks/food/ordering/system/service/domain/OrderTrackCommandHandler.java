package com.vakaks.food.ordering.system.service.domain;

import com.vakaks.food.ordering.system.entity.Order;
import com.vakaks.food.ordering.system.exception.OrderNotFoundException;
import com.vakaks.food.ordering.system.service.domain.dto.track.TrackOrderQuery;
import com.vakaks.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import com.vakaks.food.ordering.system.service.domain.mapper.OrderDataMapper;
import com.vakaks.food.ordering.system.service.domain.port.output.repository.OrderRepository;
import com.vakaks.food.ordering.system.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Slf4j
@Component
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery){
        Optional<Order> orderResult =
                orderRepository
                        .findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));

        if (orderResult.isEmpty()){
            log.warn("Could not find order with id {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Could not find order with id " +
                    trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    };
}
