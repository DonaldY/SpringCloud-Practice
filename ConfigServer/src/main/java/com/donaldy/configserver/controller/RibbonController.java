package com.donaldy.configserver.controller;

import com.donaldy.configserver.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author donald
 * @date 2020/5/5
 */
@RestController
public class RibbonController {

    private RibbonService ribbonService;

    @Autowired
    public RibbonController(RibbonService ribbonService) {

        this.ribbonService = ribbonService;
    }

    @GetMapping("/ping")
    public String ping() {

        return this.ribbonService.ping();
    }

}
