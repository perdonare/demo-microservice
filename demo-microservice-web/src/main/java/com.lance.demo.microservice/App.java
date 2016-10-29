package com.lance.demo.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * Created by perdonare on 2016/10/27.
 */
@SpringBootApplication
@EnableCircuitBreaker
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
