package com.vakaks.food.ordering.system.service.domain;

import com.vakaks.food.ordering.system.event.OrderCreatedEvent;
import com.vakaks.food.ordering.system.service.domain.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 2/16/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Slf4j
@Component
public class OrderCreatedEventApplicationListener {

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderCreatedEventApplicationListener(OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }


    @TransactionalEventListener
    public void process(OrderCreatedEvent orderCreatedEvent){
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    }
}
