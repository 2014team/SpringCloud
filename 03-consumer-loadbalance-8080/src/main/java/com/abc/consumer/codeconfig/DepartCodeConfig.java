package com.abc.consumer.codeconfig;

import com.abc.consumer.irule.CustomRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DepartCodeConfig {

    // 开启消息者端的负载均衡功能，默认是轮询策略
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 指定Ribbon使用随机算法策略
    // @Bean
    // public IRule loadBalanceRule() {
    //     return new RandomRule();
    // }

    // 指定Ribbon使用随机算法策略
//    @Bean
//    public IRule loadBalanceRule() {
//        List<Integer> ports = new ArrayList<>();
//        ports.add(8083);
//        return new CustomRule(ports);
//    }


}
