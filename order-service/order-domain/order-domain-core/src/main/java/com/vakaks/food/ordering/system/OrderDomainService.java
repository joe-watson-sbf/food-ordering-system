package com.vakaks.food.ordering.system;

import com.vakaks.food.ordering.system.entity.Order;
import com.vakaks.food.ordering.system.entity.Restaurant;
import com.vakaks.food.ordering.system.event.OrderCancelledEvent;
import com.vakaks.food.ordering.system.event.OrderCreatedEvent;
import com.vakaks.food.ordering.system.event.OrderPaidEvent;

import java.util.List;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/10/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);
    OrderPaidEvent payOrder(Order order);
    void approvedOrder(Order order);
    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);
    void cancelOrder(Order order, List<String> failureMessages);
}
