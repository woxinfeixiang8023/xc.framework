package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class XcLearningCourse implements Serializable {
    //
    private String id;
    //课程id
    private String courseId;
    //用户id
    private String userId;
    //收费规则
    private String charge;
    //课程价格
    private Double price;
    //有效性
    private String valid;
    //
    private String startTime;
    //
    private String endTime;
    //选课状态
    private String status;
//get set 方法
    public void setId (String  id){
    this.id=id;
    }
    public  String getId(){
    return this.id;
    }
    public void setCourseId (String  courseId){
    this.courseId=courseId;
    }
    public  String getCourseId(){
    return this.courseId;
    }
    public void setUserId (String  userId){
    this.userId=userId;
    }
    public  String getUserId(){
    return this.userId;
    }
    public void setCharge (String  charge){
    this.charge=charge;
    }
    public  String getCharge(){
    return this.charge;
    }
    public void setPrice (Double  price){
    this.price=price;
    }
    public  Double getPrice(){
    return this.price;
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
    public void setStatus (String  status){
    this.status=status;
    }
    public  String getStatus(){
    return this.status;
    }
}
