package com.bjpowernode.front.controller;

import com.bjpowernode.service.PlatBaseInfoService;
import com.bjpowernode.service.ProductInfoService;
import com.bjpowernode.service.InvestService;
import com.bjpowernode.front.service.SmsService;
import com.bjpowernode.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * Package:com.bjpowernode.front.controller
 * Date:2022/3/1 16:28
 */
public class BaseController {

    //声明公共的方法，属性的等

    @DubboReference(interfaceClass = PlatBaseInfoService.class, version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    @DubboReference(interfaceClass = ProductInfoService.class, version = "1.0")
    protected ProductInfoService productInfoService;

    //投资服务
    @DubboReference(interfaceClass = InvestService.class,version = "1.0")
    protected InvestService investService;

    @DubboReference(interfaceClass = UserService.class,version = "1.0")
    protected  UserService userService;

    @Resource
    protected StringRedisTemplate stringRedisTemplate;
}
