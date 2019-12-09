package com.dragon.miaosha.dao;

import com.dragon.miaosha.dataobject.SequenceDO;
import org.springframework.stereotype.Component;

@Component("SequenceDOMapper")
public interface SequenceDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbg.generated Mon Dec 09 15:56:41 CST 2019
     */
    int deleteByPrimaryKey(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbg.generated Mon Dec 09 15:56:41 CST 2019
     */
    int insert(SequenceDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbg.generated Mon Dec 09 15:56:41 CST 2019
     */
    int insertSelective(SequenceDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbg.generated Mon Dec 09 15:56:41 CST 2019
     */
//    SequenceDO selectByPrimaryKey(String name);
    SequenceDO getSequenceByName(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbg.generated Mon Dec 09 15:56:41 CST 2019
     */
    int updateByPrimaryKeySelective(SequenceDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequence_info
     *
     * @mbg.generated Mon Dec 09 15:56:41 CST 2019
     */
    int updateByPrimaryKey(SequenceDO record);
}