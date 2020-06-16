package com.zb.feign;


public class XcOrders {

  private Integer id;
  private String orderNumber;
  private String payNumber;
  private String status;
  private Integer payType;
  private String userId;
  private String itemId;
  private Integer itemNum;
  private Double initialPrice;
  private Double itemPrice;
  private String valid;
  private String startTime;
  private String endTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }


  public String getPayNumber() {
    return payNumber;
  }

  public void setPayNumber(String payNumber) {
    this.payNumber = payNumber;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }


  public Integer getItemNum() {
    return itemNum;
  }

  public void setItemNum(Integer itemNum) {
    this.itemNum = itemNum;
  }


  public Double getInitialPrice() {
    return initialPrice;
  }

  public void setInitialPrice(Double initialPrice) {
    this.initialPrice = initialPrice;
  }


  public Double getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(Double itemPrice) {
    this.itemPrice = itemPrice;
  }


  public String getValid() {
    return valid;
  }

  public void setValid(String valid) {
    this.valid = valid;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

}
