package com.vakaks.food.ordering.system.event;

import com.vakaks.food.ordering.system.entity.Order;

import java.time.ZonedDateTime;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/10/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public class OrderCancelledEvent extends OrderEvent {

    public OrderCancelledEvent(Order order, ZonedDateTime createAt) {
        super(order, createAt);
    }
}
