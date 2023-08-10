package com.bjpowernode.micrdataservice.mapper;

import com.bjpowernode.model.BidInfo;
import com.bjpowernode.pojo.BidInfoProduct;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    BigDecimal selectSumBidMoney();

    /*某个产品的投资记录*/
    List<BidInfoProduct> selectByProductId(@Param("productId") Integer productId,
                                           @Param("offset") int offset,
                                           @Param("rows") Integer rows);
}