package com.lance.demo.microservice.controller;

import com.lance.demo.microservice.config.hystrix.CloudCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by perdonare on 2016/10/27.
 */
@RestController
public class DemoController {
    @Autowired
    private CloudCommand cloudCommand;

    @RequestMapping("/hystrix")
    public String hystrix() {
        return cloudCommand.getStores(null);
    }

    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println(name);
        return name;
    }
}
