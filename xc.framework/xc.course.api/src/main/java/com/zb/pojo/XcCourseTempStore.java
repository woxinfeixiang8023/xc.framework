package com.zb.pojo;

import java.io.Serializable;

/***
 *   实时库存表
 */
public class XcCourseTempStore implements Serializable {
    //
    private Long id;
    //商品id
    private String courseId;
    //记录时间
    private String recordDate;
    //库存
    private Integer store;
    //状态（0：抢购 1：无效的信息 2：支付成功）
    private Integer status;
    //
    private String creationDate;
    //
    private String createdBy;
    //
    private String modifyDate;
    //
    private Long modifiedBy;

    //get set 方法
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordDate() {
        return this.recordDate;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getStore() {
        return this.store;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyDate() {
        return this.modifyDate;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getModifiedBy() {
        return this.modifiedBy;
    }
}
