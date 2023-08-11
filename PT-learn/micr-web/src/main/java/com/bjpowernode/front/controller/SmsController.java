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
            boolean sendSmsYes = smsService.sendSms(phone);
            if (sendSmsYes) {
                result = RespResult.ok();
            }
        }else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }

}
