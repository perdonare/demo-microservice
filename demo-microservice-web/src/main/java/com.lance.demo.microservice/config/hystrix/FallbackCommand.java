package com.lance.demo.microservice.config.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Created by perdonare on 2016/10/27.
 */
public class FallbackCommand extends HystrixCommand<String>{
    private final String name;

    public FallbackCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        throw new Exception("this command always fails");
    }

    @Override
    protected String getFallback() {
        return  "Fallback " + name + "!";
    }
}
