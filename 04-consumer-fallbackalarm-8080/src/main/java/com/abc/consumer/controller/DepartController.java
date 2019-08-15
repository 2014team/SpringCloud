package com.abc.consumer.controller;

import com.abc.consumer.bean.Depart;
import com.abc.consumer.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private DepartService service;
    @Autowired
    private StringRedisTemplate template;

    // 创建一个线程池，包含5个线程
    private ForkJoinPool pool = new ForkJoinPool(5);

    @HystrixCommand(fallbackMethod = "getHystrixHandle")
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id, HttpServletRequest request) {
        return service.getDepartById(id);
    }

    public Depart getHystrixHandle(@PathVariable("id") int id, HttpServletRequest request) {
        // 获取当前发生降级的消费者主机的IP
        String ip = request.getLocalAddr();
        String key = ip + "_getDepartById";
        queryCache(key);

        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return depart;
    }

    // 异常发生后的报警
    private void queryCache(String key) {
        // 获取Redis操作对象
        BoundValueOperations<String, String> ops = template.boundValueOps(key);
        String value = ops.get();
        if(value == null) {
            synchronized (this) {
                value = ops.get();
                if(value == null) {
                    // 使用线程池实现异步短信发送
                    sendFallbackMsg(key);
                    // 使用value变量存在线程安全问题
                    // value = "短信已发送";
                    ops.set("短信已发送", 10, TimeUnit.SECONDS);
                }
            }
        }
    }

    // 使用线程池实现异步短信发送
    private void sendFallbackMsg(String key) {

        pool.submit(() -> {
            System.out.println("发送服务异常报警短信：" + key);
        });


    }

}
