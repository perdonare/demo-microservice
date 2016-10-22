package com.lance.demo.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * Created by perdonare on 2016/10/22.
 */
@Component
public class AppArgsBean {
    @Autowired
    public AppArgsBean(ApplicationArguments args) {
        System.out.println( "参数:");
        System.out.println( args.getOptionNames());
    }
}
