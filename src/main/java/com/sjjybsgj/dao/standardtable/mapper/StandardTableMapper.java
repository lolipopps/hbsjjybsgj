package com.sjjybsgj.dao.standardtable.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sjjybsgj.dao.standardtable.model.StandardTable;
import com.sjjybsgj.dao.standardtable.model.StandardTableExample;

public interface StandardTableMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    long countByExample(StandardTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    int deleteByExample(StandardTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    int deleteByPrimaryKey(String columnRuleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    int insert(StandardTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    int insertSelective(StandardTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    List<StandardTable> selectByExample(StandardTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    StandardTable selectByPrimaryKey(String columnRuleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    int updateByExampleSelective(@Param("record") StandardTable record, @Param("example") StandardTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    int updateByExample(@Param("record") StandardTable record, @Param("example") StandardTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    int updateByPrimaryKeySelective(StandardTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table standard_table
     *
     * @mbg.generated Sun May 27 19:04:09 CST 2018
     */
    int updateByPrimaryKey(StandardTable record);
}