package com.vakaks.food.ordering.system.exception;

import com.vakaks.food.ordering.system.domain.exception.DomainException;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 2/16/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
