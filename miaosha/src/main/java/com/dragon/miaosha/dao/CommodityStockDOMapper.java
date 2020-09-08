package com.dragon.miaosha.dao;

import com.dragon.miaosha.dataobject.CommodityStockDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component(value = "CommodityStockDOMapper")
public interface CommodityStockDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity_stock
     *
     * @mbg.generated Thu Dec 05 14:38:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity_stock
     *
     * @mbg.generated Thu Dec 05 14:38:00 CST 2019
     */
    int insert(CommodityStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity_stock
     *
     * @mbg.generated Thu Dec 05 14:38:00 CST 2019
     */
    int insertSelective(CommodityStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity_stock
     *
     * @mbg.generated Thu Dec 05 14:38:00 CST 2019
     */
    CommodityStockDO selectByPrimaryKey(Integer id);
    CommodityStockDO selectByCommodityId(Integer commodityId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity_stock
     *
     * @mbg.generated Thu Dec 05 14:38:00 CST 2019
     */
    int updateByPrimaryKeySelective(CommodityStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity_stock
     *
     * @mbg.generated Thu Dec 05 14:38:00 CST 2019
     */
    int updateByPrimaryKey(CommodityStockDO record);

    int decreaseStock(@Param("commodityId")Integer commodityId, @Param("amount") Integer amount);
}