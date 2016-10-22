package com.lance.demo.microservice.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by perdonare on 2016/10/22.
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //再容器启动完成的时候才进行调用
        System.out.println("application runner args:" + args.getSourceArgs().toString());
    }
}
