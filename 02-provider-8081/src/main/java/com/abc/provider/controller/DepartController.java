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

    // 注入服务发现客户端
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        return service.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean deleteHandle(@PathVariable("id") int id) {
        return service.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean updateHandle(@RequestBody Depart depart) {
        return service.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        return service.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandle() {
        return service.listAllDeparts();
    }

    @GetMapping("/discovery")
    public Object discoveryHandle() {
        // 获取到服务注册列表中所有微服务名称，即spring.application.name属性的值
        List<String> springApplicationNames = client.getServices();

        // 遍历所有微服务
        for(String name: springApplicationNames) {
            // 由于一个微服务可以有多个主机提供，所以这里获取到的是一个集合
            List<ServiceInstance> instances = client.getInstances(name);
            // 遍历该微服务所有主机的详情
            for(ServiceInstance instance: instances) {
                String host = instance.getHost();
                int port = instance.getPort();
                System.out.println(host + " : " + port);
            }
        }
        return springApplicationNames;
    }
}
