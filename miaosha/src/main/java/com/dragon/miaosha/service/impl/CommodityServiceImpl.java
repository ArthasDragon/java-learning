package com.dragon.miaosha.service.impl;

import com.dragon.miaosha.dao.CommodityDOMapper;
import com.dragon.miaosha.dao.CommodityStockDOMapper;
import com.dragon.miaosha.dataobject.CommodityDO;
import com.dragon.miaosha.dataobject.CommodityStockDO;
import com.dragon.miaosha.error.BusinessException;
import com.dragon.miaosha.error.EmBusinessError;
import com.dragon.miaosha.service.CommodityService;
import com.dragon.miaosha.service.model.CommodityModel;
import com.dragon.miaosha.validator.ValidationResult;
import com.dragon.miaosha.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private CommodityDOMapper commodityDOMapper;

    @Autowired
    private CommodityStockDOMapper commodityStockDOMapper;

    private CommodityDO convertCommodityDOFromCommodityModel(CommodityModel commodityModel) {
        if (commodityModel == null) {
            return null;
        }
        CommodityDO commodityDO = new CommodityDO();
        BeanUtils.copyProperties(commodityModel, commodityDO);
        commodityDO.setPrice(commodityModel.getPrice().doubleValue());

        return commodityDO;
    }

    private CommodityStockDO convertCommodityStockDOFromCommodityModel(CommodityModel commodityModel) {
        if (commodityModel == null) {
            return null;
        }

        CommodityStockDO commodityStockDO = new CommodityStockDO();

        commodityStockDO.setCommodityId(commodityModel.getId());
        commodityStockDO.setStock(commodityModel.getStock());

        return commodityStockDO;
    }

    private CommodityModel convertModelFromDataObject(CommodityDO commodityDO, CommodityStockDO commodityStockDO) {
        CommodityModel commodityModel = new CommodityModel();

        BeanUtils.copyProperties(commodityDO, commodityModel);

        commodityModel.setPrice(new BigDecimal(commodityDO.getPrice()));
        commodityModel.setStock(commodityStockDO.getStock());

        return commodityModel;
    }

    @Override
    @Transactional
    public CommodityModel createCommodity(CommodityModel commodityModel) throws BusinessException {
        // 校验入参逻辑
        ValidationResult validationResult = validator.validate(commodityModel);

        if (validationResult.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrMsg());
        }

        // 转化CommodityModel -> dataobject
        CommodityDO commodityDO = convertCommodityDOFromCommodityModel(commodityModel);

        // 写入数据库
        commodityDOMapper.insertSelective(commodityDO);
        commodityModel.setId(commodityDO.getId()); // 写入后获取commodity_id加入model中
        CommodityStockDO commodityStockDO = convertCommodityStockDOFromCommodityModel(commodityModel); // 转换commodityStockDO对象

        commodityStockDOMapper.insertSelective(commodityStockDO);

        // 返回创建的对象

        return getCommodityById(commodityDO.getId());
    }

    @Override
    public List<CommodityModel> listCommodity() {
        List<CommodityDO> commodityDOList = commodityDOMapper.listCommodity();
        List<CommodityModel> commodityModelList = commodityDOList.stream().map(commodityDO -> {
            CommodityStockDO commodityStockDO = commodityStockDOMapper.selectByCommodityId(commodityDO.getId());
            CommodityModel commodityModel = convertModelFromDataObject(commodityDO, commodityStockDO);
            return commodityModel;
        }).collect(Collectors.toList());
        return commodityModelList;
    }

    @Override
    public CommodityModel getCommodityById(Integer id) {
        CommodityDO commodityDO = commodityDOMapper.selectByPrimaryKey(id);

        if (commodityDO == null) {
            return null;
        }

        // 获得库存数量
        CommodityStockDO commodityStockDO = commodityStockDOMapper.selectByCommodityId(commodityDO.getId());

        // dataobject 转化成model
        CommodityModel commodityModel = convertModelFromDataObject(commodityDO, commodityStockDO);

        return commodityModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer commodityId, Integer amount) throws BusinessException {
        int affectedRow = commodityStockDOMapper.decreaseStock(commodityId, amount);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }
}
