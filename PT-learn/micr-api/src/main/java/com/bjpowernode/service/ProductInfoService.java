package com.bjpowernode.service;

import com.bjpowernode.model.LoanInfo;
import com.bjpowernode.pojo.MultiProduct;

import java.util.List;

/**
 * @author csd
 * @version 1.0
 */
@SuppressWarnings({"all"})
public interface ProductInfoService {
    /*根据产品类型，查询产品，支持分页*/
    List<LoanInfo> queryByTypeLimit(Integer pType,Integer pageNo,Integer pageSize);

    /*某个产品类型的记录总数*/
    Integer queryRecordNumsByType(Integer pType);

    /*首页的多个产品数据*/
    MultiProduct queryIndexPageProducts();

    /*根据id查询商品信息*/
    LoanInfo queryById(Integer id);
    
}
