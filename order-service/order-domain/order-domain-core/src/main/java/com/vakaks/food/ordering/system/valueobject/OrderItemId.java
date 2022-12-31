package com.vakaks.food.ordering.system.valueobject;

import com.vakaks.food.ordering.system.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {

    public OrderItemId(Long value) {
        super(value);
    }
}
