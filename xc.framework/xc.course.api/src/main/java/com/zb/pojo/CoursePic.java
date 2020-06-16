package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class CoursePic implements Serializable {
    //课程id
    private String courseid;
    //图片id
    private String pic;
//get set 方法
    public void setCourseid (String  courseid){
    this.courseid=courseid;
    }
    public  String getCourseid(){
    return this.courseid;
    }
    public void setPic (String  pic){
    this.pic=pic;
    }
    public  String getPic(){
    return this.pic;
    }
}
