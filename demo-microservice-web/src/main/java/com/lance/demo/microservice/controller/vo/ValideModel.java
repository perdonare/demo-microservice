package com.lance.demo.microservice.controller.vo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by perdonare on 2016/10/26.
 */
public class ValideModel {
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private Date date;

    private String name ;

    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
