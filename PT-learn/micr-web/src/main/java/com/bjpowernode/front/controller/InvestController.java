package com.bjpowernode.front.controller;

import com.bjpowernode.common.constants.RedisKey;
import com.bjpowernode.common.utils.CommonUtils;
import com.bjpowernode.front.view.RespResult;
import com.bjpowernode.front.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Package:com.bjpowernode.front.controller
 * Date:2022/3/4 9:22
 * 有关投资功能
 */
@Api(tags = "投资理财产品")
@CrossOrigin
@RestController
public class InvestController extends BaseController {

    /*投资排行榜*/
    @ApiOperation(value = "投资排行榜",notes = "显式投资金额最高的3位用户信息")
    @GetMapping("/v1/invest/rank")
    public RespResult showInvestRank(){
        //从redis查询数据
        Set<ZSetOperations.TypedTuple<String>> sets = stringRedisTemplate
                .boundZSetOps(RedisKey.KEY_INVEST_RANK).reverseRangeWithScores(0, 2);

        List<RankView> rankList = new ArrayList<>();
        //遍历set集合
        sets.forEach( tuple ->{
            //tuple.getValue();//手机号
            //tuple.getScore();//投资金额
            rankList.add(new RankView(CommonUtils.tuoMinPhone(tuple.getValue()),tuple.getScore()));
        });

        RespResult  result = RespResult.ok();
        result.setList(rankList);
        return result;
    }
}
