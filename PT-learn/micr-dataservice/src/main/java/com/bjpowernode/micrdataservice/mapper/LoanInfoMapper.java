package com.bjpowernode.micrdataservice.mapper;

import com.bjpowernode.model.LoanInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LoanInfoMapper {
    BigDecimal selectAvgRate();

    int deleteByPrimaryKey(Integer id);

    int insert(LoanInfo record);

    int insertSelective(LoanInfo record);

    LoanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanInfo record);

    int updateByPrimaryKey(LoanInfo record);
    /*按产品类型分页查询*/
    List<LoanInfo> selectByTypeLimit(@Param("ptype") Integer ptype,
                                     @Param("offset") Integer offset,
                                     @Param("rows") Integer rows);

    Integer selectCountByType(@Param("ptype") Integer pType);

    /*首页的多个产品数据*/

}