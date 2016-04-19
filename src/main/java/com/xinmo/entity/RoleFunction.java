package com.xinmo.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoleFunction  implements Serializable{

    private static final long serialVersionUID = 7999762360843923531L;

    private Integer id;

    private Integer roleId;

    private Integer functionId;

    private Date createTime;

    public RoleFunction() {
    }

    public RoleFunction(Integer id) {
        this.id = id;
    }
    public RoleFunction(Integer roleId,Integer functionId) {
    	this.roleId = roleId;
    	this.functionId = functionId;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }


    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
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
        if (obj instanceof RoleFunction == false)
            return false;
        if (this == obj)
            return true;
        RoleFunction other = (RoleFunction) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }
}
