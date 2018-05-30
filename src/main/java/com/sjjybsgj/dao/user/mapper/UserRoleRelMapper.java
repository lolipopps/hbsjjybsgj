package com.sjjybsgj.dao.user.mapper;

import com.sjjybsgj.dao.user.model.UserRoleRel;
import com.sjjybsgj.dao.user.model.UserRoleRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    long countByExample(UserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    int deleteByExample(UserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    int deleteByPrimaryKey(String relId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    int insert(UserRoleRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    int insertSelective(UserRoleRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    List<UserRoleRel> selectByExample(UserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    UserRoleRel selectByPrimaryKey(String relId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    int updateByExampleSelective(@Param("record") UserRoleRel record, @Param("example") UserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    int updateByExample(@Param("record") UserRoleRel record, @Param("example") UserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    int updateByPrimaryKeySelective(UserRoleRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_ROLE_REL
     *
     * @mbg.generated Wed Sep 20 16:58:25 CST 2017
     */
    int updateByPrimaryKey(UserRoleRel record);
}