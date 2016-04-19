package com.xinmo.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Role  implements Serializable{
   
    private static final long serialVersionUID = -1906682184478132253L;
    
    private Integer id;
    private String name;
    private String description;
    private java.util.Date createTime;
    private Integer checked;

    private String functionIds;//拥有的功能
    
    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public Integer getChecked() {
        return this.checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    
    public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("Id", getId()).append("Name", getName())
            .append("Description", getDescription())
            .append("CreateTime", getCreateTime()).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Role == false)
            return false;
        if (this == obj)
            return true;
        Role other = (Role) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }
}
