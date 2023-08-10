package com.bjpowernode.micrdataservice.service;

import com.bjpowernode.common.constants.YLBConstant;
import com.bjpowernode.micrdataservice.mapper.LoanInfoMapper;
import com.bjpowernode.model.LoanInfo;
import com.bjpowernode.pojo.MultiProduct;
import com.bjpowernode.service.ProductInfoService;
import com.bjpowernode.common.utils.CommonUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author csd
 * @version 1.0
 */
@SuppressWarnings({"all"})
@DubboService(interfaceClass = ProductInfoService.class,version = "1.0")
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource
    private LoanInfoMapper loanInfoMapper;

    @Override
    public List<LoanInfo> queryByTypeLimit(Integer pType, Integer pageNo, Integer pageSize) {
        List<LoanInfo> loanInfos = new ArrayList<>();
        if( pType == 0 || pType == 1 || pType == 2){
            pageNo = CommonUtils.defaultPageNo(pageNo);
            pageSize = CommonUtils.defaultPageSize(pageSize);
            int offset  = (pageNo - 1) * pageSize;
            loanInfos = loanInfoMapper.selectByTypeLimit(pType, offset, pageSize);
        }
        return loanInfos;
    }

    /*某个产品类型的记录总数*/
    @Override
    public Integer queryRecordNumsByType(Integer pType) {
        Integer counts = 0;
        if( pType == 0 || pType == 1 || pType == 2){
            counts  = loanInfoMapper.selectCountByType(pType);
        }
        return counts;
    }

    /*首页的多个产品数据*/
    @Override
    public MultiProduct queryIndexPageProducts() {
        MultiProduct result = new MultiProduct();

        //查询新手宝
        List<LoanInfo> xinShouBaoList  = loanInfoMapper.selectByTypeLimit(
                YLBConstant.PRODUCT_TYPE_XINSHOUBAO,0,1);
        System.out.println(xinShouBaoList);
        //查询优选
        List<LoanInfo> youXuanList = loanInfoMapper.selectByTypeLimit(
                YLBConstant.PRODUCT_TYPE_YOUXUAN,0,3 );
        System.out.println(youXuanList);

        //散标
        List<LoanInfo> sanBiaoList = loanInfoMapper.selectByTypeLimit(
                YLBConstant.PRODUCT_TYPE_SANBIAO,0,3 );
        System.out.println(sanBiaoList);
        result.setXinShouBao(xinShouBaoList);
        result.setYouXuan(youXuanList);
        result.setSanBiao(sanBiaoList);
        return result;
    }

    @Override
    public LoanInfo queryById(Integer id) {
        LoanInfo loanInfo = null;
        if( id != null && id > 0 ){
            loanInfo = loanInfoMapper.selectByPrimaryKey(id);
        }
        return loanInfo;
    }
}
