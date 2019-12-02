package com.dragon.miaosha.dao;

import com.dragon.miaosha.dataobject.UserDO;
import org.springframework.stereotype.Component;

@Component(value = "UserDOMapper")
public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Dec 02 10:37:31 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Dec 02 10:37:31 CST 2019
     */
    int insert(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Dec 02 10:37:31 CST 2019
     */
    int insertSelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Dec 02 10:37:31 CST 2019
     */
    UserDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Dec 02 10:37:31 CST 2019
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Dec 02 10:37:31 CST 2019
     */
    int updateByPrimaryKey(UserDO record);
}