package com.wang.service;


import com.wang.entity.Payment;

/**
 * @author zzyy
 * @date 2020/2/18 10:40
 **/
public interface PaymentService {

    String paymentCircuitBreaker(Integer id);

    String paymentOk(Integer id);

    String paymentTimeOut(Integer id);
}
