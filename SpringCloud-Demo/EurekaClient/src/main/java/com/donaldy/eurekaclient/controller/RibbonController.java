package com.donaldy.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author donald
 * @date 2020/5/5
 */
@RestController
public class RibbonController {

    @Value("${server.port}")
    private int port;

    @GetMapping("ping")
    public String ping() {

        return "ping eureka-client, port: " + port;
    }
}
