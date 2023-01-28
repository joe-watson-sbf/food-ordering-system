package com.vakaks.food.ordering.system.service.domain.dto.create;

import com.vakaks.food.ordering.system.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/27/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {

    @NotNull
    private final UUID orderTrackingId;
    @NotNull
    private final OrderStatus orderStatus;
    @NotNull
    private final String message;


}
