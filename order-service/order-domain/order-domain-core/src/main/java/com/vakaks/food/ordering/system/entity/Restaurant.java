package com.vakaks.food.ordering.system.entity;

import com.vakaks.food.ordering.system.domain.entity.AggregateRoot;
import com.vakaks.food.ordering.system.domain.valueobject.RestaurantId;

import java.util.List;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 12/30/2022
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private final boolean active;

    private Restaurant(Builder builder) {
        super.setId(builder.restaurantId);
        products = builder.products;
        active = builder.active;
    }


    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public static final class Builder {
        private RestaurantId restaurantId;
        private List<Product> products;
        private boolean active;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
