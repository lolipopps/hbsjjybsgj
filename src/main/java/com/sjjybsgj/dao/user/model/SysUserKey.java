package com.sjjybsgj.dao.user.model;

import java.io.Serializable;

public class SysUserKey implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.USER_ID
     *
     * @mbg.generated Thu Sep 14 18:04:32 CST 2017
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_USER.USER_CODE
     *
     * @mbg.generated Thu Sep 14 18:04:32 CST 2017
     */
    private String userCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SYS_USER
     *
     * @mbg.generated Thu Sep 14 18:04:32 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.USER_ID
     *
     * @return the value of SYS_USER.USER_ID
     *
     * @mbg.generated Thu Sep 14 18:04:32 CST 2017
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.USER_ID
     *
     * @param userId the value for SYS_USER.USER_ID
     *
     * @mbg.generated Thu Sep 14 18:04:32 CST 2017
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_USER.USER_CODE
     *
     * @return the value of SYS_USER.USER_CODE
     *
     * @mbg.generated Thu Sep 14 18:04:32 CST 2017
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_USER.USER_CODE
     *
     * @param userCode the value for SYS_USER.USER_CODE
     *
     * @mbg.generated Thu Sep 14 18:04:32 CST 2017
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_USER
     *
     * @mbg.generated Thu Sep 14 18:04:32 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userCode=").append(userCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}