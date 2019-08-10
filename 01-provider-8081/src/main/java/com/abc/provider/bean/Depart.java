package com.abc.provider.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * java转json hibernate懒加载造成的无限递归问题 ----------------------
 * hibernate对象懒加载，json序列化失败,因为懒加载这个对象属性只是一个代理对象，如果json直接当作一个存在的属性去序列化就会出现错误
 * 需要在实体类上加上
 * @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
 */
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Depart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

}
