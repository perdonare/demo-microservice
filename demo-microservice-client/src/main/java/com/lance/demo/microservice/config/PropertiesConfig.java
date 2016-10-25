package com.lance.demo.microservice.config;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by perdonare on 2016/10/22.
 */
@Profile("dev")
// @ConfigurationProperties(prefix = "demo",locations = "classpath:config/demo.properties")
@ConfigurationProperties(prefix = "demo")
//@Component
public class PropertiesConfig {
    @NotNull
    private String name;
    //@NotNull无法检测数字
    //@Min(1)
    private int age;

    private String test_name;
    @Valid
    @NotNull
    private MyInnerProperty innerProperty;

    public static class MyInnerProperty{
        @NotEmpty
        private String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public MyInnerProperty getInnerProperty() {
        return innerProperty;
    }

    public void setInnerProperty(MyInnerProperty innerProperty) {
        this.innerProperty = innerProperty;
    }
}
