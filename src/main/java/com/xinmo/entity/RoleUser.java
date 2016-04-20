package com.xinmo.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoleUser  implements Serializable {
    private static final long serialVersionUID = -5647752654563613177L;
    private Integer id;
    private Integer roleId;
    private Long userId;
    private String roleName;
    private java.util.Date createTime;

    public RoleUser() {
    }

    public RoleUser(Integer id) {
        this.id = id;
    }

    public RoleUser(long userId, int roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("CreateTime", getCreateTime()).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RoleUser == false)
            return false;
        if (this == obj)
            return true;
        RoleUser other = (RoleUser) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }
}
