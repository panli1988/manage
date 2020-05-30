package com.lp.framework.manage.model;

import java.io.Serializable;

/**
 * d_user_role
 * @author 
 */
public class UserRole implements Serializable {
    private String userCode;

    private String roleCode;

    private static final long serialVersionUID = 1L;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public UserRole(String userCode, String roleCode) {
        this.userCode = userCode;
        this.roleCode = roleCode;
    }
}