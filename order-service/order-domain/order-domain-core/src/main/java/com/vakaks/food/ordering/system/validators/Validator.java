package com.vakaks.food.ordering.system.validators;

import com.vakaks.food.ordering.system.entity.Order;
import com.vakaks.food.ordering.system.entity.Product;
import com.vakaks.food.ordering.system.entity.Restaurant;
import com.vakaks.food.ordering.system.exception.OrderDomainException;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/10/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public class Validator {

    public static void setOrderProductInformation(Order order, Restaurant restaurant) {

        order.getItems()
                .forEach(orderItem -> restaurant.getProducts().forEach(restaurantProduct -> {
                    Product currentProduct = orderItem.getProduct();
                    if(currentProduct.equals(restaurantProduct)){
                        currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(),
                                restaurantProduct.getPrice());
                    }
                })
        );
    }

    public static void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()){
            throw new OrderDomainException("Restaurant with id " + restaurant.getId().getValue()
             + " is currently not active!");
        }
    }
}
