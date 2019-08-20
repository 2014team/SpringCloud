package com.abc.provider.service;

import com.abc.provider.bean.Depart;
import com.abc.provider.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RefreshScope
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartRepository repository;
    // 读取配置文件中的属性值
    @Value("${server.port}")
    private int port;

    @Value("${flag}")
    private String flag;

    @Override
    public Depart getDepartById(int id) {
        // getOne()方法：若其指定的id不存在，该方法将抛出异常
        if(repository.existsById(id)) {
            Depart depart = repository.getOne(id);
            // 部门名称 + falga
            depart.setName(flag + "   " + depart.getName() + port);
            return depart;
        }
        return null;
    }

}
