package com.vakaks.food.ordering.system.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/27/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

@Getter
@Builder
@AllArgsConstructor
public class OrderAddress {
    @NotNull @Max(value = 50)
    private final String street;
    @NotNull @Max(value = 10)
    private final String postalCode;
    @NotNull @Max(value = 50)
    private final String city;
}
