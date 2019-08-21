package com.abc.consumer.controller;

import com.abc.consumer.bean.Depart;
import com.abc.consumer.service.DepartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private DepartService service;

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        log.info("消费者的处理器方法被调用");
        return service.getDepartById(id);
    }

}
