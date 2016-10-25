package com.lance.demo.microservice.controller;

/**
 * Created by perdonare on 2016/10/20.
 */
import com.lance.demo.microservice.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @Autowired
    private PropertiesConfig propertiesConfig;
    @RequestMapping("/admin")
    public String index() {
        System.out.println(propertiesConfig.getAge()+propertiesConfig.getName()+propertiesConfig.getInnerProperty().getAddress()+propertiesConfig.getTest_name());
        return "Greetings from Spring Boot!";
    }

}
