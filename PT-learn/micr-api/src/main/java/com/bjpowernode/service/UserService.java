package com.bjpowernode.service;

import com.bjpowernode.model.User;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 14:52
 * @Description
 */
public interface UserService {
    /**
     * 根据手机号来查询数据
     */
    User queryByPhone(String phone);
}
