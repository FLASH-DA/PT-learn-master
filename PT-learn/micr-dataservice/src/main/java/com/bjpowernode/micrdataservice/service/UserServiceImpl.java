package com.bjpowernode.micrdataservice.service;

import com.bjpowernode.common.utils.CommonUtils;
import com.bjpowernode.micrdataservice.mapper.UserMapper;
import com.bjpowernode.model.User;
import com.bjpowernode.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 14:54
 * @Description
 */
@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User queryByPhone(String phone) {
        User user=null;
        if (CommonUtils.checkphone(phone)) {
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }
}
