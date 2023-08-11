package com.bjpowernode.micrdataservice.service;

import com.bjpowernode.service.SmsService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 15:57
 * @Description
 */
@DubboService(interfaceClass = SmsService.class,version = "1.0")
public class SmsServiceImpl implements SmsService {
    @Override
    public boolean sendSms(String phone) {
        return false;
    }
}
