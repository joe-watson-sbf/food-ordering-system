package com.vakaks.food.ordering.system.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
public class OrderItem {
    @NotNull
    private final UUID productId;
    @NotNull
    private final Integer quantity;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final BigDecimal subTotal;

}
