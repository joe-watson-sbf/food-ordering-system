package com.vakaks.food.ordering.system.service.domain;

import com.vakaks.food.ordering.system.event.OrderCreatedEvent;
import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.vakaks.food.ordering.system.service.domain.mapper.OrderDataMapper;
import com.vakaks.food.ordering.system.service.domain.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/30/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderCreateHelper orderCreateHelper;
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderCreateCommandHandler(OrderDataMapper orderDataMapper, OrderCreateHelper orderCreateHelper,
                                     OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderDataMapper = orderDataMapper;
        this.orderCreateHelper = orderCreateHelper;
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);

        // PUBLISH THE EVENT
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }



}
