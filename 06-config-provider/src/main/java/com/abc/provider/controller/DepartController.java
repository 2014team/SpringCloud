package com.abc.provider.controller;

import com.abc.provider.bean.Depart;
import com.abc.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/provider/depart")
@RestController
public class DepartController {
    @Autowired
    private DepartService service;

    @Autowired
    private DiscoveryClient client;


    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        return service.getDepartById(id);
    }

    @GetMapping("/discovery")
    public Object discoveryHandle() {
        // 获取服务注册列表中所有微服务名称
        List<String> springApplicationNames = client.getServices();
        for(String name: springApplicationNames) {
            // 获取提供指定微服务名称服务的所有提供者主机
            List<ServiceInstance> instances = client.getInstances(name);
            for(ServiceInstance instance: instances) {
                String host = instance.getHost();
                int port = instance.getPort();
                System.out.println(host + " : " + port);
            }
        }
        return springApplicationNames;
    }
}
