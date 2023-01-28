package com.vakaks.food.ordering.system.service.domain.port.input.service;

import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.vakaks.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.vakaks.food.ordering.system.service.domain.dto.track.TrackOrderQuery;
import com.vakaks.food.ordering.system.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
