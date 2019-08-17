package com.abc.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ConsumerFallback implements FallbackProvider {

    // 指定要降级的微服务名称
    @Override
    public String getRoute() {
        // 对所有微服务降级
        return "*";
        // 仅对指定的微服务进行降级
        // return "abcmsc-consumer-depart-8080";
    }

    // 定制降级响应
    // route参数：当前请求所访问的微服务名称
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        System.out.println("route = " + route);
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // 返回状态常量
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 返回状态码，这里为503
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            @Override
            public String getStatusText() throws IOException {
                // 返回状态码对应的状态短语，这里为"Service Unavailable"
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() { }

            @Override
            public InputStream getBody() throws IOException {
                // 设置降级信息
                // String msg = "fallback:" + ConsumerFallback.this.getRoute();
                String msg = "fallback:" + route;
                return new ByteArrayInputStream(msg.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // 设置降级响应头信息
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
