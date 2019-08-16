package com.abc.consumer.controller;

import com.abc.consumer.bean.Depart;
import com.abc.consumer.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private DepartService service;

    @HystrixCommand(fallbackMethod = "getHystrixHandle")
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        return service.getDepartById(id);
    }

    public Depart getHystrixHandle(@PathVariable("id") int id) {
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart-8090");
        return depart;
    }

    @HystrixCommand(fallbackMethod = "listHandleHystrix")
    @GetMapping("/list")
    public List<Depart> listHandle() {
        return service.listAllDeparts();
    }

    public List<Depart> listHandleHystrix() {
        ArrayList<Depart> departs = new ArrayList<>();
        departs.add(new Depart("no this depart-8090"));
        return departs;
    }

}
