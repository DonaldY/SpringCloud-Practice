package com.donaldy.gateway;


import com.donaldy.gateway.utils.RequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean(name = "ideKeyResolver")
    public IdeKeyResolver ideKeyResolver() {

        return new IdeKeyResolver();
    }

    class IdeKeyResolver implements KeyResolver {

        @Override
        public Mono<String> resolve(ServerWebExchange exchange) {

            ServerHttpRequest request = exchange.getRequest();

            String contentType = request.getHeaders().getFirst("Content-Type");

            if (request.getMethod() != HttpMethod.POST || "multipart/form-data".equals(contentType)) {

                return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
            }

            String bodyContent = RequestUtil.resolveBodyFromRequest(exchange.getRequest());

            ObjectMapper JSON_MAPPER = new ObjectMapper();

            try {
                Test test = JSON_MAPPER.readValue(bodyContent, Test.class);

                System.out.println("test appId : " + test.getAppId());

                return Mono.just(test.getAppId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        }

    }
}

/**
 * {
 *   "appId": "20190828163922073733756",
 *   "orgId": "org_1331212",
 *   "responseType": "code"
 * }
 */
@Data
class Test {

    private String appId;
    private String orgId;
    private String responseType;
}