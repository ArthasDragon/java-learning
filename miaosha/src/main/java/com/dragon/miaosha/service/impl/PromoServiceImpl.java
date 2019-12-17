package com.dragon.miaosha.service.impl;

import com.dragon.miaosha.dao.PromoDOMapper;
import com.dragon.miaosha.dataobject.PromoDO;
import com.dragon.miaosha.service.PromoService;
import com.dragon.miaosha.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByCommodityId(Integer commodityId) {
        // 获取对应商品的秒杀活动信息
        PromoDO promoDO = promoDOMapper.selectByCommodityId(commodityId);

        // dataobject -> model
        PromoModel promoModel = convertFromPromoDO(promoDO);
        if (promoModel == null) {
            return null;
        }

        // 判断当前时间是否秒杀时间即将开始或正在进行
        if (promoModel.getStartDate().isAfterNow()) {
            promoModel.setStatus(1);
        } else if (promoModel.getEndDate().isBeforeNow()) {
            promoModel.setStatus(3);
        }else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    private PromoModel convertFromPromoDO(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }

        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setPromoCommodityPrice(new BigDecimal(promoDO.getPromoCommodityPrice()));
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));

        return promoModel;
    }

}
