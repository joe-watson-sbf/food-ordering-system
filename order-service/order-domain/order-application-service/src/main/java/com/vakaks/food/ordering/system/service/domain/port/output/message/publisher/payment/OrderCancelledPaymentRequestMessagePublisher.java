package com.vakaks.food.ordering.system.service.domain.port.output.message.publisher.payment;

import com.vakaks.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.vakaks.food.ordering.system.event.OrderCancelledEvent;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
