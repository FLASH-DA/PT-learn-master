package com.bjpowernode.front.service;

/**
 * Package:com.bjpowernode.front.service
 * Date:2022/3/9 11:16
 */
public interface SmsService {


    /**
     * @param phone 手机号
     * @return true：发送成功，false 其他情况
     */
    boolean sendSms(String phone);


    /**
     * @param phone 手机号
     * @param code  提交参数中的验证码
     * @return
     */
    boolean checkSmsCode(String phone,String code);
}
