package com.abc.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy    // 开启zuul代理模式
@SpringBootApplication
public class ZuulRatelimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulRatelimitApplication.class, args);
    }

}
