package com.abc.consumer.service;


import com.abc.consumer.bean.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
// 指定当前Service所绑定的提供者微服务名称
@FeignClient(value = "abcmsc-provider-depart")
@RequestMapping("/provider/depart")
public interface DepartService {
    @GetMapping("/get/{id}")
    Depart getDepartById(@PathVariable("id") int id);
}
