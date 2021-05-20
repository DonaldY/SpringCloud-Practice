package com.donald.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务A 接口
 *
 * @author donald
 * @date 2021/05/20
 */
@RestController
public class ServiceController {

    @RequestMapping(value = "/sayHello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name) {
        System.out.println("被调用了一次");
        return "{'msg': 'hello, " + name + "'}";
    }
}
