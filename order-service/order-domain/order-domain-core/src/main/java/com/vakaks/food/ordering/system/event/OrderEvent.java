package com.vakaks.food.ordering.system.event;

import com.vakaks.food.ordering.system.domain.event.DomainEvent;
import com.vakaks.food.ordering.system.entity.Order;

import java.time.ZonedDateTime;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/10/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createAt;

    public OrderEvent(Order order, ZonedDateTime createAt) {
        this.order = order;
        this.createAt = createAt;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }
}
