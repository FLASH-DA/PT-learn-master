package com.bjpowernode.front.controller;

import com.bjpowernode.common.enums.RCode;
import com.bjpowernode.common.utils.CommonUtils;
import com.bjpowernode.front.view.RespResult;
import com.bjpowernode.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 14:38
 * @Description
 */
@Api(tags = "用户功能")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {
    /**
     * 手机号是否存在
     */
    @ApiOperation(value = "手机号是否注册过",notes = "在注册功能中，判断手机号是否可以注册" )
    @ApiImplicitParam(name = "phone",value = "手机号")
    @GetMapping("/phone/exists")
    public RespResult phoneExists(@RequestParam("phone") String phone) {
        RespResult result = RespResult.fail();
        result.setRCode(RCode.PHONE_EXISTS);
        if (CommonUtils.checkphone(phone)) {
            User user = userService.queryByPhone(phone);
            if (user==null) {
                result =RespResult.ok();
            }

        }else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }

}
