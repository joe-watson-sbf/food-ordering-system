package com.vakaks.food.ordering.system.service.domain.port.output.repository;

import com.vakaks.food.ordering.system.entity.Customer;

import java.util.Optional;
import java.util.UUID;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
