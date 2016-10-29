package com.lance.demo.microservice.config.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

/**
 * Created by perdonare on 2016/10/27.
 */
@Component
public class CloudCommand {
    @HystrixCommand(fallbackMethod = "defaultStores")
    public String getStores(Map<String, Object> parameters) {
        double a = Math.random();
        System.out.println("value:" + a);
        if (a >0.8) {
            return "ok";
        }
        throw new RuntimeException("fail");
    }

    public String defaultStores(Map<String, Object> parameters) {
        return  "fail  invoke";
    }
}
