package com.bjpowernode.micrdataservice.service;

import com.bjpowernode.common.utils.CommonUtils;
import com.bjpowernode.micrdataservice.mapper.BidInfoMapper;
import com.bjpowernode.pojo.BidInfoProduct;
import com.bjpowernode.service.InvestService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 11:03
 * @Description
 */
@DubboService(interfaceClass = InvestService.class,version = "1.0")
public class InvestServiceImpl implements InvestService {
    @Resource
    private BidInfoMapper bidInfoMapper;
    @Override
    public List<BidInfoProduct> queryBidListByProductId(Integer productId, Integer pageNo, Integer pageSize) {
        List<BidInfoProduct> bidList = new ArrayList<>();
        if(productId!=null && productId>0){
            pageNo = CommonUtils.defaultPageNo(pageNo);
            pageSize = CommonUtils.defaultPageSize(pageSize);
            int offset = (pageNo-1*pageSize);
            bidList = bidInfoMapper.selectByProductId(productId,pageNo,pageSize);
        }
        return bidList;
    }
}
