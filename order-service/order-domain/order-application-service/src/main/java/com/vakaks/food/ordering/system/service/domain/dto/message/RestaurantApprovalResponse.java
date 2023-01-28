package com.vakaks.food.ordering.system.service.domain.dto.message;

import com.vakaks.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.Instant;
import java.util.List;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/27/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String restaurantId;
    private Instant createAt;
    private OrderApprovalStatus orderApprovalStatus;
    private List<String> failureMessages;
}
