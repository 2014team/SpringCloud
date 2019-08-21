package com.abc.provider.controller;

import com.abc.provider.bean.Depart;
import com.abc.provider.service.DepartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/provider/depart")
@RestController
public class DepartController {
    @Autowired
    private DepartService service;
    // 注入服务发现客户端

    @Autowired
    private DiscoveryClient client;
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        log.info("生产者的处理器方法被调用");
        return service.getDepartById(id);
    }
}
