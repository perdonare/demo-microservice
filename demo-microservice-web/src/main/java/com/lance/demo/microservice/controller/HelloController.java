package com.lance.demo.microservice.controller;

import com.lance.demo.microservice.controller.vo.ValideModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by perdonare on 2016/10/26.
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "hello world";
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "hello world";
    }

/*    *//**
     * {path} 和 变量path
     * @param path
     * @return
     *//*
    @RequestMapping(value = "/{path}",method = RequestMethod.GET)
    public String helloPath(@PathVariable String path) {
        return "hello world" + path;
    }*/

    /**
     * {path} 和 {date} 重复  路径一致 无法映射
     * @param date
     * @return
     */
    @RequestMapping(value = "/date/{date}",method = RequestMethod.GET)
    public String helloDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return "hello world" + date.toString();
    }

    /**
     * 参数校验
     * @param valideModel
     * @return
     */
    @RequestMapping(value = "/valide",method = RequestMethod.POST)
    public String helloValide(@RequestBody @Valid ValideModel valideModel) {
        return "hello world" + valideModel.getName();
    }

    @RequestMapping(value = "/consumes", method = RequestMethod.POST, consumes="application/json")
    public String consumes(@RequestBody @Valid ValideModel valideModel) {
        return "hello world" + valideModel.getName();
    }

    @RequestMapping(value = "/produces", method = RequestMethod.POST, produces="application/json")
    public String produces(@RequestBody @Valid ValideModel valideModel) {
        return "hello world" + valideModel.getName();
    }

    @RequestMapping(value = "/headers", method = RequestMethod.POST, headers="Referer=http:/www.baidu.com/")
    public String headers(@RequestBody @Valid ValideModel valideModel) {
        return "hello world" + valideModel.getName()+valideModel.getDate().toString();
    }


    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String param(@RequestParam(value = "aaa",required = true) String inputString) {
        return "hello world" + inputString;
    }
}
