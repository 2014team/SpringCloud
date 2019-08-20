package com.abc.consumer.controller;

import com.abc.consumer.bean.Depart;
import com.abc.consumer.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
@RefreshScope
public class DepartController {
    @Autowired
    private DepartService service;

    @Value("${suffix}")
    private String suffix;

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        Depart depart = service.getDepartById(id);
        if(depart == null) {
            depart = new Depart();
            depart.setId(id);
            depart.setName(suffix );
        }
        return depart;
    }

}
