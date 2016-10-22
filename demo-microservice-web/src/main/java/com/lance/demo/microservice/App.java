package com.lance.demo.microservice;

import com.lance.demo.microservice.listeners.MyApplicationReadyEventListener;
import com.lance.demo.microservice.listeners.MyApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
//使用debug模式 可以在启动选项上加 --debug  或 idea里vm选项加 -Ddebug
/**
 * Created by perdonare on 2016/10/20.
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        //初始化spring application
        args = new String[]{"my args1","my args2"};
        SpringApplication springApplication = new SpringApplication(App.class);

        //用途??
        springApplication.setLogStartupInfo(false);
        //添加监听器
        springApplication.addListeners(new MyApplicationStartedEventListener(),new MyApplicationReadyEventListener());
        //获取spring环境配置
        ApplicationContext ctx = springApplication.run(args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
