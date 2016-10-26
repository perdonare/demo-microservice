package com.lance.demo.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
/**
 * Created by perdonare on 2016/10/26.
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(App.class,args);
    }
}
