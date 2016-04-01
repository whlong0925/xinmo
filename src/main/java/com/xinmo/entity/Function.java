package com.xinmo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Function implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String path;
    private String action;
    private Integer status = 0;//功能开关 0启用 1禁用
    private Integer parentId;
    private String description;
    private Integer functionType;//0 模板 1功能 2子功能
    private Integer sequence;
    private Date createTime;
    /**
     * 父功能下的子功能
     */
    private List<Function> functionList = new ArrayList<>();
    
    private int isChecked;
    
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

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFunctionType() {
        return this.functionType;
    }

    public void setFunctionType(Integer functionType) {
        this.functionType = functionType;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Function> getFunctionList() {
        return this.functionList;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }

    public int getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("Id", getId()).append("Name", this.getName())
            .append("Description", getDescription()).append("Path", getPath())
            .append("Action", getAction()).append("Status", getStatus())
            .append("ParentId", getParentId())
            .append("FunctionType", getFunctionType())
            .append("Sequence", getSequence())
            .append("CreateTime", getCreateTime()).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Function == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Function other = (Function) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }
}
