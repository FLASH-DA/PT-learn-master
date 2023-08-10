package com.bjpowernode.front.controller;

import com.bjpowernode.common.enums.RCode;
import com.bjpowernode.common.utils.CommonUtils;
import com.bjpowernode.front.view.PageInfo;
import com.bjpowernode.front.view.RespResult;
import com.bjpowernode.model.LoanInfo;
import com.bjpowernode.pojo.BidInfoProduct;
import com.bjpowernode.pojo.MultiProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author csd
 * @version 1.0
 */
@SuppressWarnings({"all"})
@Api(tags = "理财产品功能")
@CrossOrigin
@RestController
@RequestMapping("/v1")
public class ProductController extends BaseController {
    @ApiOperation(value = "首页三类产品列表", notes = "一个新手宝，三个优选，三个散标产品")
    @GetMapping("/product/index")
    public RespResult queryProductIndex() {
        RespResult result = RespResult.ok();
        MultiProduct multiProduct = productInfoService.queryIndexPageProducts();
        result.setData(multiProduct);
        return result;
    }

    /*按产品类型分页查询*/
    @ApiOperation(value = "产品分页查询",notes = "按产品类型分页查询")
    @GetMapping("/product/list")
    public RespResult queryProductByType(@RequestParam("ptype") Integer pType,
                                         @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                                         @RequestParam(value = "pageSize",required = false,defaultValue = "9") Integer pageSize){
        RespResult result = RespResult.fail();
        if(pType != null && (pType == 0 || pType == 1 || pType == 2)){
            pageNo = CommonUtils.defaultPageNo(pageNo);
            pageSize = CommonUtils.defaultPageSize(pageSize);
            //分页处理，记录总数
            Integer recordNums = productInfoService.queryRecordNumsByType(pType);
            if( recordNums > 0 ){
                //产品集合
                List<LoanInfo> productInfos = productInfoService.queryByTypeLimit(pType,pageNo,pageSize);
                //构建PageInfo
                PageInfo page = new PageInfo(pageNo,pageSize,recordNums);

                result = RespResult.ok();
                result.setList(productInfos);
                result.setPage(page);
            }
        } else {
            //请求参数有误
            result.setRCode(RCode.REQUEST_PRODUCT_TYPE_ERR);
        }
        return result;

    }

    /*查询某个产品的详情和投资记录*/
    @ApiOperation(value = "产品详情",notes = "查询某个产品的详细信息和投资5条记录")
    @GetMapping("/product/info")
    public RespResult queryProductDetail(@RequestParam("productId") Integer id){
        System.out.println(id);
        RespResult result = RespResult.fail();
        if( id != null && id > 0 ){
            //调用产品查询
            LoanInfo loanInfo = productInfoService.queryById(id);
            if( loanInfo != null){
                //查询投资记录
                List<BidInfoProduct> bidInfoProductList = investService.queryBidListByProductId(id, 1, 5);
                System.out.println("bidInfoProductList = " + bidInfoProductList);
                //查询成功
                result = RespResult.ok();
                result.setData(loanInfo);
                result.setList(bidInfoProductList);
            } else {
                result.setRCode(RCode.PRODUCT_OFFLINE);
            }
        }

        return result;
    }
}
