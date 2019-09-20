package com.example.gateway.filter.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;


@Configuration
public class RateLimiterConfiguration {
//1 uri 100 requests
    @Bean
    @Primary
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }

    @Bean
    public KeyResolver testResolver(){
        return exchange -> Mono.just(exchange.getSession().subscribe().toString());
    }
    /**
     * IP limit
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }


    /**
     * api limit
     * @return
     */
//    @Bean
//    @Primary
//    public KeyResolver apiKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getPath().pathWithinApplication().value());
//    }


}
