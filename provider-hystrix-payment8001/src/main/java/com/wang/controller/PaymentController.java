package com.wang.controller;

import com.wang.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentTimeOut(id);
        log.info("result + " + result);
        return result;
    }

    @GetMapping("/hystrix/ok/{id}")
    public String ok(@PathVariable("id") Integer id) {
        String result = paymentService.paymentOk(id);
        log.info("result + " + result);
        return result;
    }
}
