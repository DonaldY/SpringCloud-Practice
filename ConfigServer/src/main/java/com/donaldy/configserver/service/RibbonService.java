package com.donaldy.configserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author donald
 * @date 2020/5/5
 */
@Service
public class RibbonService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;


    public String ping() {

        return this.restTemplate.getForObject("http://eureka-client/ping", String.class);
    }
}
