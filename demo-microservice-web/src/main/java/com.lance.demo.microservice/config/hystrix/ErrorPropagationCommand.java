package com.lance.demo.microservice.config.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * Created by perdonare on 2016/10/27.
 */
public class ErrorPropagationCommand extends HystrixCommand<String>{
    private final String name;

    public ErrorPropagationCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run()  {
        throw new HystrixBadRequestException("sssssss");//除了此异常外其他异常都不会抛出 HystrixBadRequestException
        // throw new RuntimeException("sssssss");
    }

    @Override
    protected String getFallback() {
        return  "Fallback " + name + "!";
    }
}
