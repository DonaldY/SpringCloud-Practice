package com.donaldy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean(name = "remoteAddrKeyResolver")
    public RemoteAddrKeyResolver remoteAddrKeyResolver() {

        return new RemoteAddrKeyResolver();
    }

    class RemoteAddrKeyResolver implements KeyResolver {

        @Override
        public Mono<String> resolve(ServerWebExchange exchange) {

            return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        }

    }
}
