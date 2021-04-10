package com.wang.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wang.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zzyy
 * @date 2020/2/18 10:40
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentOk " + id + " 哈哈";
    }

    /**
     * 一旦服务方法失败并抛出错误信息后，会自动调用#HystrixCommand标注好
     * 的fallbackMethod调用类中的指定方法
     */
    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentTimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentTimeOut " + id + "哈哈，耗时3秒";
    }

    public String timeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentTimeOut " + id + "呜呜呜~~~";
    }
}
