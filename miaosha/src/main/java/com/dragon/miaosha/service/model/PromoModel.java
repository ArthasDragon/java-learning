package com.dragon.miaosha.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class PromoModel {
    private Integer id;

    // 秒杀活动状态  1 - 未开始  2 - 进行中  3 - 已结束
    private Integer status;

    // 秒杀活动名称
    private String promoName;

    // 秒杀开始时间
    private DateTime startDate;

    // 秒杀结束时间
    private DateTime endDate;

    // 秒杀活动的适用商品
    private Integer commodityId;

    // 秒杀活动的商品价格
    private BigDecimal promoCommodityPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public BigDecimal getPromoCommodityPrice() {
        return promoCommodityPrice;
    }

    public void setPromoCommodityPrice(BigDecimal promoCommodityPrice) {
        this.promoCommodityPrice = promoCommodityPrice;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
