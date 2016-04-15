package com.xinmo.dao;

import com.xinmo.entity.App;
import com.xinmo.entity.AppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int countByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int deleteByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int insert(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int insertSelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    List<App> selectByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    App selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int updateByExample(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int updateByPrimaryKeySelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ss_app
     *
     * @mbggenerated Fri Apr 15 16:44:46 GMT+08:00 2016
     */
    int updateByPrimaryKey(App record);
}