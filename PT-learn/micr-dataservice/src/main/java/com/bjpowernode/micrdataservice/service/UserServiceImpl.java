package com.bjpowernode.micrdataservice.service;

import com.bjpowernode.common.utils.CommonUtils;
import com.bjpowernode.micrdataservice.mapper.FinanceAccountMapper;
import com.bjpowernode.micrdataservice.mapper.UserMapper;
import com.bjpowernode.model.FinanceAccount;
import com.bjpowernode.model.User;
import com.bjpowernode.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 14:54
 * @Description
 */
@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Value("${ylb.config.password-salt}")
    private String passwordSalt;
    @Resource
    private FinanceAccountMapper financeAccountMapper;
    @Override
    public User queryByPhone(String phone) {
        User user=null;
        if (CommonUtils.checkphone(phone)) {
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }
    /*用户注册*/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized int userRegister(String phone, String password) {
        int result = 0;//默认参数不正确
        if( CommonUtils.checkphone(phone)
                && (password != null && password.length()==32)){

            //判断手机号在库中是否存在
            User queryUser = userMapper.selectByPhone(phone);
            if(queryUser == null){
                //注册密码的md5二次加密。 给原始的密码加盐（salt)
                String newPassword = DigestUtils.md5Hex( password + passwordSalt);

                //注册u_user
                User user = new User();
                user.setPhone(phone);
                user.setLoginPassword(newPassword);
                user.setAddTime(new Date());
                userMapper.insertReturnPrimaryKey(user);

                //获取主键user.getId()
                FinanceAccount account = new FinanceAccount();
                account.setUid(user.getId());
                account.setAvailableMoney(new BigDecimal("0"));
                financeAccountMapper.insertSelective(account);
                //成功result = 1
                result = 1;
            } else {
                //手机号存在
                result = 2;
            }
        }
        return result;
    }
}
