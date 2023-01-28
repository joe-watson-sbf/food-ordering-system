package com.vakaks.food.ordering.system.service.domain.port.output.repository;

import com.vakaks.food.ordering.system.entity.Restaurant;

import java.util.Optional;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
