package com.dragon.miaosha.service.model;

import java.math.BigDecimal;

// 用户下单的交易类型
public class OrderModel {

    // 交易号
    private String id;

    // 用户userid
    private Integer userId;

    // 商品id
    private Integer commodityId;

    // 若非空，表示已秒杀商品方式下单
    private Integer promoId;

    // 购买商品的单价，promoId非空表示秒杀商品价格
    private BigDecimal commodityPrice;

    // 购买数量
    private Integer amount;

    // 购买金额
    private BigDecimal orderPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}
