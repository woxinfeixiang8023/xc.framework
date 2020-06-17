package com.zb.pojo;

import java.io.Serializable;

/***
 *
 */
public class XcOrders implements Serializable {
    //主键
    private Long id;
    //订单号
    private String orderNumber;
    //支付系统订单号
    private String payNumber;
    //交易状态(0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评)
    private Integer status;
    //支付方式:1:支付宝 2:微信
    private Integer payType;
    //用户id
    private String userId;
    //商品id
    private String itemId;
    //商品数量
    private Integer itemNum;
    //定价
    private Double initialPrice;
    //金额
    private Double itemPrice;
    //课程有效性
    private String valid;
    //课程开始时间
    private String startTime;
    //课程结束时间
    private String endTime;
    //token
    private String token;

    //get set 方法
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public String getPayNumber() {
        return this.payNumber;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayType() {
        return this.payType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Integer getItemNum() {
        return this.itemNum;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Double getInitialPrice() {
        return this.initialPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getItemPrice() {
        return this.itemPrice;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getValid() {
        return this.valid;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
