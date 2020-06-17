package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class XcOrdersDetail implements Serializable {
    //
    private String id;
    //订单号
    private String orderNumber;
    //商品id
    private String itemId;
    //商品数量
    private Integer itemNum;
    //金额
    private Double itemPrice;
    //课程有效性
    private String valid;
    //课程开始时间
    private String startTime;
    //课程结束时间
    private String endTime;
//get set 方法
    public void setId (String  id){
    this.id=id;
    }
    public  String getId(){
    return this.id;
    }
    public void setOrderNumber (String  orderNumber){
    this.orderNumber=orderNumber;
    }
    public  String getOrderNumber(){
    return this.orderNumber;
    }
    public void setItemId (String  itemId){
    this.itemId=itemId;
    }
    public  String getItemId(){
    return this.itemId;
    }
    public void setItemNum (Integer  itemNum){
    this.itemNum=itemNum;
    }
    public  Integer getItemNum(){
    return this.itemNum;
    }
    public void setItemPrice (Double  itemPrice){
    this.itemPrice=itemPrice;
    }
    public  Double getItemPrice(){
    return this.itemPrice;
    }
    public void setValid (String  valid){
    this.valid=valid;
    }
    public  String getValid(){
    return this.valid;
    }
    public void setStartTime (String  startTime){
    this.startTime=startTime;
    }
    public  String getStartTime(){
    return this.startTime;
    }
    public void setEndTime (String  endTime){
    this.endTime=endTime;
    }
    public  String getEndTime(){
    return this.endTime;
    }
}
