package com.sjjybsgj.dao.role.model;

import java.io.Serializable;

public class RoleMenuRel implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE_MENU_REL.REL_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    private String relId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE_MENU_REL.ROLE_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    private String roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE_MENU_REL.MENU_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    private String menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ROLE_MENU_REL
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE_MENU_REL.REL_ID
     *
     * @return the value of ROLE_MENU_REL.REL_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    public String getRelId() {
        return relId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE_MENU_REL.REL_ID
     *
     * @param relId the value for ROLE_MENU_REL.REL_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    public void setRelId(String relId) {
        this.relId = relId == null ? null : relId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE_MENU_REL.ROLE_ID
     *
     * @return the value of ROLE_MENU_REL.ROLE_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE_MENU_REL.ROLE_ID
     *
     * @param roleId the value for ROLE_MENU_REL.ROLE_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE_MENU_REL.MENU_ID
     *
     * @return the value of ROLE_MENU_REL.MENU_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE_MENU_REL.MENU_ID
     *
     * @param menuId the value for ROLE_MENU_REL.MENU_ID
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLE_MENU_REL
     *
     * @mbg.generated Tue Sep 19 11:16:58 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", relId=").append(relId);
        sb.append(", roleId=").append(roleId);
        sb.append(", menuId=").append(menuId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}