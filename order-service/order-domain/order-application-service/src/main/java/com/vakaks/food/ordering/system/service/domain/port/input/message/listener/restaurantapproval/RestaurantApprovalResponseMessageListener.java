package com.vakaks.food.ordering.system.service.domain.port.input.message.listener.restaurantapproval;

import com.vakaks.food.ordering.system.service.domain.dto.message.RestaurantApprovalResponse;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public interface RestaurantApprovalResponseMessageListener {
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);
    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
