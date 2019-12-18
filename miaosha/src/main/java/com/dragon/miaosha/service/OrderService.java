package com.dragon.miaosha.service;

import com.dragon.miaosha.error.BusinessException;
import com.dragon.miaosha.service.model.OrderModel;

public interface OrderService {
    OrderModel createOrder(Integer userId, Integer commodityId, Integer promoId, Integer amount) throws BusinessException;
}
