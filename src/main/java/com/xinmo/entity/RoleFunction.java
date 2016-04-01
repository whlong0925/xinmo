package com.xinmo.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoleFunction {

    private Integer id;

    private Role role;

    private Function function;

    private Date createTime;

    public RoleFunction() {
    }

    public RoleFunction(Integer id) {
        this.id = id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Function getFunction() {
        return this.function;
    }

    public void setFunction(Function function) {
        this.function = function;
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
