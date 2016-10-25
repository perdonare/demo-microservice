package com.lance.demo.microservice.config;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perdonare on 2016/10/24.
 */
//@Configuration
public class MyEndpoint {
    @Bean
    public Endpoint createMvcEndpoint() {
        EndpointBean endpoint = new EndpointBean("mypoint",true,true);
        return endpoint;
    }

    class EndpointBean  extends AbstractEndpoint<List<Object>> {

        public EndpointBean(String id, boolean sensitive, boolean enabled) {
            super(id, sensitive, enabled);
        }

        @Override
        public List<Object> invoke() {
            List<Object> list = new ArrayList<>();
            list.add("test1");
            list.add("test2");
            list.add("test3");
            list.add("test4");
            return list;
        }
    }
}
