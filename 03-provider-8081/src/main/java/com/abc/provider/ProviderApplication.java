package com.abc.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  对于Spring Boot工程要注册为Eureka的客户端，除了要导入指定的依赖外，还可以使用以下两个注解之一
 *  @EnableEurekaClient：注册中心只能是Eureka
 *  @EnableDiscoveryClient：注册中心除了Eureka之外 ，还可以是其它，例如zk
 */
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
