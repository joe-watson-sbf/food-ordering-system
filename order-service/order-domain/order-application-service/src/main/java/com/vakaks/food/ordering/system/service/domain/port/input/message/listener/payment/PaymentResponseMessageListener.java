package com.vakaks.food.ordering.system.service.domain.port.input.message.listener.payment;

import com.vakaks.food.ordering.system.service.domain.dto.message.PaymentResponse;

/*******************************************************************************
 * All right reserved Joe Watson SBF
 * Copyright (c) 1/28/2023
 * Github: https://github.com/joe-watson-sbf
 *
 ******************************************************************************/

public interface PaymentResponseMessageListener {
    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentCancelled(PaymentResponse paymentResponse);
}
