package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class TeachplanMedia implements Serializable {
    //课程计划id
    private String teachplanId;
    //媒资文件id
    private String mediaId;
    //媒资文件的原始名称
    private String mediaFileoriginalname;
    //媒资文件访问地址
    private String mediaUrl;
    //课程Id
    private String courseid;
//get set 方法
    public void setTeachplanId (String  teachplanId){
    this.teachplanId=teachplanId;
    }
    public  String getTeachplanId(){
    return this.teachplanId;
    }
    public void setMediaId (String  mediaId){
    this.mediaId=mediaId;
    }
    public  String getMediaId(){
    return this.mediaId;
    }
    public void setMediaFileoriginalname (String  mediaFileoriginalname){
    this.mediaFileoriginalname=mediaFileoriginalname;
    }
    public  String getMediaFileoriginalname(){
    return this.mediaFileoriginalname;
    }
    public void setMediaUrl (String  mediaUrl){
    this.mediaUrl=mediaUrl;
    }
    public  String getMediaUrl(){
    return this.mediaUrl;
    }
    public void setCourseid (String  courseid){
    this.courseid=courseid;
    }
    public  String getCourseid(){
    return this.courseid;
    }
}
