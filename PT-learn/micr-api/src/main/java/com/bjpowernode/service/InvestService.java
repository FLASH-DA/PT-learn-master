package com.bjpowernode.service;

import com.bjpowernode.pojo.BidInfoProduct;

import java.util.List;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 10:58
 * @Description
 */
public interface InvestService {
    /** 查询某个产品的投资记录 */
    List<BidInfoProduct> queryBidListByProductId(Integer productId,
                                                 Integer pageNo,
                                                 Integer pageSize);

}
