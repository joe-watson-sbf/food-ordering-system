package com.vakaks.food.ordering.system.service.domain;

import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.vakaks.food.ordering.system.service.domain.dto.track.TrackOrderQuery;
import com.vakaks.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import com.vakaks.food.ordering.system.service.domain.port.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
