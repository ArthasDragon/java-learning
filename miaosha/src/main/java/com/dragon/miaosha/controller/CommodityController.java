package com.dragon.miaosha.controller;

import com.dragon.miaosha.controller.viewobject.CommodityVO;
import com.dragon.miaosha.error.BusinessException;
import com.dragon.miaosha.response.CommonReturnType;
import com.dragon.miaosha.service.impl.CommodityServiceImpl;
import com.dragon.miaosha.service.model.CommodityModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller("commodity")
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

    @Autowired
    private CommodityServiceImpl commodityService;

    // 创建商品
    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType createCommodity(@RequestParam(name = "title") String title,
                                            @RequestParam(name = "description") String description,
                                            @RequestParam(name = "price") BigDecimal price,
                                            @RequestParam(name = "stock") Integer stock,
                                            @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {

        // 封装service请求用来创建商品
        CommodityModel commodityModel = new CommodityModel();
        commodityModel.setTitle(title);
        commodityModel.setDescription(description);
        commodityModel.setPrice(price);
        commodityModel.setStock(stock);
        commodityModel.setImgUrl(imgUrl);

        CommodityModel returnModel = commodityService.createCommodity(commodityModel);

        CommodityVO commodityVO = convertVOFromModel(returnModel);

        return CommonReturnType.create(commodityVO);
    }

    // 商品详情页浏览
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getCommodity(@RequestParam(name = "id")Integer id){

        CommodityModel commodityModel = commodityService.getCommodityById(id);

        CommodityVO commodityVO = convertVOFromModel(commodityModel);

        return CommonReturnType.create(commodityVO);
    }

    // 商品列表页面浏览
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listCommodity(){
        List<CommodityModel> commodityModelList = commodityService.listCommodity();

        // 使用streamapi将list内的model转为vo
        List<CommodityVO> commodityVOList = commodityModelList.stream().map(commodityModel -> {
            CommodityVO commodityVO = convertVOFromModel(commodityModel);
            return commodityVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(commodityVOList);
    }

    private CommodityVO convertVOFromModel(CommodityModel commodityModel) {
        if (commodityModel == null) {
            return null;
        }
        CommodityVO commodityVO = new CommodityVO();
        BeanUtils.copyProperties(commodityModel, commodityVO);
        return commodityVO;
    }
}
