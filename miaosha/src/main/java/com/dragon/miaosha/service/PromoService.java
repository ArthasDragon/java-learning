package com.dragon.miaosha.service;

import com.dragon.miaosha.service.model.PromoModel;

public interface PromoService {
    // 根据commodityId获取即将进行或者正在进行的秒杀活动
    PromoModel getPromoByCommodityId(Integer commodityId);
}
