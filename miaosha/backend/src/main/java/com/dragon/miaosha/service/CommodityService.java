package com.dragon.miaosha.service;

import com.dragon.miaosha.error.BusinessException;
import com.dragon.miaosha.service.model.CommodityModel;

import java.util.List;

public interface CommodityService {

    // 创建商品
    CommodityModel createCommodity(CommodityModel commodityModel) throws BusinessException;

    // 商品列表浏览
    List<CommodityModel> listCommodity();

    // 商品详情浏览
    CommodityModel getCommodityById(Integer id);

    // 库存扣减
    boolean decreaseStock(Integer commodityId, Integer amount) throws BusinessException;

    //商品销量增加
    void increaseSales(Integer commodityId, Integer amount)throws BusinessException;

}
