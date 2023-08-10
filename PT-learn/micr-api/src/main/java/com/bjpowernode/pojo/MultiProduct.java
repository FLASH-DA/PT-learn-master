package com.bjpowernode.pojo;

import com.bjpowernode.model.LoanInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author csd
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class MultiProduct implements Serializable {
    private List<LoanInfo> xinShouBao;
    private List<LoanInfo> youXuan;
    private List<LoanInfo> sanBiao;

    public List<LoanInfo> getXinShouBao() {
        return xinShouBao;
    }

    public void setXinShouBao(List<LoanInfo> xinShouBao) {
        this.xinShouBao = xinShouBao;
    }

    public List<LoanInfo> getYouXuan() {
        return youXuan;
    }

    public void setYouXuan(List<LoanInfo> youXuan) {
        this.youXuan = youXuan;
    }

    public List<LoanInfo> getSanBiao() {
        return sanBiao;
    }

    public void setSanBiao(List<LoanInfo> sanBiao) {
        this.sanBiao = sanBiao;
    }
}
