package com.bjpowernode.front;

import com.bjpowernode.front.view.RespResult;
import com.bjpowernode.model.LoanInfo;
import com.bjpowernode.pojo.MultiProduct;
import com.bjpowernode.service.ProductInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MicrWebApplicationTests {
    @DubboReference(interfaceClass = ProductInfoService.class, version = "1.0")
    protected ProductInfoService productInfoService;

    @Test
    void testQueryById() {
        Integer id = 1;
        LoanInfo loanInfo = productInfoService.queryById(id);
        System.out.println("loanInfo = " + loanInfo);

    }

}
