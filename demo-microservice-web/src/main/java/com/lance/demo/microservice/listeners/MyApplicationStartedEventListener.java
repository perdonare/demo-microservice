package com.lance.demo.microservice.listeners;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by perdonare on 2016/10/22.
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SpringApplication app = event.getSpringApplication();
        app.setBannerMode(Banner.Mode.OFF);
        System.out.println("My application started");
    }
}
