package com.abc.consumer.service;


import com.abc.consumer.bean.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient("abcmsc-provider-depart")
@RequestMapping("/provider/depart")
public interface DepartService {

    boolean modifyDepart(@RequestBody Depart depart);
    @GetMapping("/get/{id}")
    Depart getDepartById(@PathVariable("id") int id);

}
