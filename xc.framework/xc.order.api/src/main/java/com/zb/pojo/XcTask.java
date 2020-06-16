package com.zb.pojo;


public class XcTask {

  private String id;
  private String createTime;
  private String updateTime;
  private String deleteTime;
  private String taskType;
  private String mqExchange;
  private String mqRoutingkey;
  private String requestBody;
  private Integer version;
  private String status;
  private String errormsg;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }


  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }


  public String getDeleteTime() {
    return deleteTime;
  }

  public void setDeleteTime(String deleteTime) {
    this.deleteTime = deleteTime;
  }


  public String getTaskType() {
    return taskType;
  }

  public void setTaskType(String taskType) {
    this.taskType = taskType;
  }


  public String getMqExchange() {
    return mqExchange;
  }

  public void setMqExchange(String mqExchange) {
    this.mqExchange = mqExchange;
  }


  public String getMqRoutingkey() {
    return mqRoutingkey;
  }

  public void setMqRoutingkey(String mqRoutingkey) {
    this.mqRoutingkey = mqRoutingkey;
  }


  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }


  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getErrormsg() {
    return errormsg;
  }

  public void setErrormsg(String errormsg) {
    this.errormsg = errormsg;
  }

}
