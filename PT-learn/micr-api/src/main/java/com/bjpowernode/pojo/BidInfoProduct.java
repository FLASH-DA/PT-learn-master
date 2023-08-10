package com.bjpowernode.pojo;

import com.bjpowernode.model.IncomeRecord;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author Ruilin Zhong
 * @Date 2023/8/10 10:59
 * @Description
 */
public class BidInfoProduct implements Serializable {
    private Integer id;
    private String phone;
    private String BidTime;
    private BigDecimal Bigmoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBidTime() {
        return BidTime;
    }

    public void setBidTime(String bidTime) {
        BidTime = bidTime;
    }

    public BigDecimal getBigmoney() {
        return Bigmoney;
    }

    public void setBigmoney(BigDecimal bigmoney) {
        Bigmoney = bigmoney;
    }

    public BidInfoProduct(Integer id, String phone, String bidTime, BigDecimal bigmoney) {
        this.id = id;
        this.phone = phone;
        BidTime = bidTime;
        Bigmoney = bigmoney;
    }
}
