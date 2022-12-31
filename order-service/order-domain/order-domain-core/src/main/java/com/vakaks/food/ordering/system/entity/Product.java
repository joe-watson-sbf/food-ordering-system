package com.vakaks.food.ordering.system.entity;

import com.vakaks.food.ordering.system.domain.entity.BaseEntity;
import com.vakaks.food.ordering.system.domain.valueobject.Money;
import com.vakaks.food.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;

    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
