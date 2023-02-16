package com.vakaks.food.ordering.system.service.domain;

import com.vakaks.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.vakaks.food.ordering.system.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 2/16/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware,
        DomainEventPublisher<OrderCreatedEvent> {

    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("OrderCreatedEvent is published for order id: {}",
                domainEvent.getOrder().getId().getValue());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
