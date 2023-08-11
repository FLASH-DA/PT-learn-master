package com.bjpowernode.front.controller;

import com.bjpowernode.common.constants.RedisKey;
import com.bjpowernode.common.enums.RCode;
import com.bjpowernode.common.utils.CommonUtils;
import com.bjpowernode.front.view.RespResult;
import com.bjpowernode.front.service.SmsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 15:49
 * @Description
 */
@RestController
@Api(tags = "短信业务")
@RequestMapping("/v1/sms")
public class SmsController extends BaseController{
    @Resource
    private SmsService smsService;

    /*@Resource(name = "smsCodeLoginImpl")
    private SmsService loginSmsService;*/

    /**发送注册验证码短信*/
    @GetMapping("/code/register")
    public RespResult sendCodeRegister(@RequestParam String phone){
        RespResult result = RespResult.fail();
        if(CommonUtils.checkphone(phone)){
            //判断redis中是否有这个手机号的验证码
            String key  = RedisKey.KEY_SMS_CODE_REG + phone;
            if(stringRedisTemplate.hasKey(key)){
                result = RespResult.ok();
                result.setRCode(RCode.SMS_CODE_CAN_USE);
            } else {
                boolean isSuccess = smsService.sendSms(phone);
                if( isSuccess ){
                    result = RespResult.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }

}
