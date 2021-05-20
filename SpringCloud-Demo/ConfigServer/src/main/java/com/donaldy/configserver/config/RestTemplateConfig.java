package com.donaldy.configserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /*@Value("${http.client.base.uri}")
    private String baseUri;

    @Value("${http.client.read-timeout}")
    private int readTimeout;

    @Value("${http.client.connect-timeout}")
    private int connectTimeout;*/

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
