package com.donaldy.eurekaclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClientService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public void test() {

        ServiceInstance serviceInstance = this.loadBalancerClient.choose("Server");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort());
        String response = this.restTemplate.getForObject(url, String.class);
    }


}
