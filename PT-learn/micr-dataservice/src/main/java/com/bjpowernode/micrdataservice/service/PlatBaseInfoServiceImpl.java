package com.bjpowernode.micrdataservice.service;

import com.bjpowernode.micrdataservice.mapper.BidInfoMapper;
import com.bjpowernode.micrdataservice.mapper.LoanInfoMapper;
import com.bjpowernode.micrdataservice.mapper.UserMapper;
import com.bjpowernode.pojo.BaseInfo;
import com.sun.org.apache.xalan.internal.xsltc.dom.SingletonIterator;
import org.apache.dubbo.config.annotation.DubboService;
import com.bjpowernode.service.PlatBaseInfoService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author csd
 * @version 1.0
 */
@SuppressWarnings({"all"})
@DubboService(interfaceClass = PlatBaseInfoService.class,version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private LoanInfoMapper loanInfoMapper;

    @Resource
    private BidInfoMapper bidInfoMapper;

    @Override
    public BaseInfo queryPlatBaseInfo() {
        //获取注册人数， 收益率平均值， 累计成交金额
        int registerUser = userMapper.selectCountUser();

        //收益率平均值
        BigDecimal avgRate = loanInfoMapper.selectAvgRate();

        //累计成交金额
        BigDecimal sumBidMoney = bidInfoMapper.selectSumBidMoney();

        BaseInfo baseInfo = new BaseInfo(avgRate,sumBidMoney,registerUser);

        return baseInfo;
    }

}
