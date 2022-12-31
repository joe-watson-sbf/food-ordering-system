package com.vakaks.food.ordering.system.valueobject;

import com.vakaks.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class TranckingId extends BaseId<UUID> {

    public TranckingId(UUID value) {
        super(value);
    }
}
