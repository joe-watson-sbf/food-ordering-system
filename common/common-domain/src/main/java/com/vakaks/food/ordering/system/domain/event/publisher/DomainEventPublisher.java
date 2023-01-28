package com.vakaks.food.ordering.system.domain.event.publisher;

import com.vakaks.food.ordering.system.domain.event.DomainEvent;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public interface DomainEventPublisher <T extends DomainEvent>{
    void publish(T domainEvent);
}
