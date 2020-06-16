package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class XcTeacher implements Serializable {
    //老师表id
    private String id;
    //用户id对应xc_user表id
    private String user_id;
    //称呼
    private String name;
    //个人简介
    private String intro;
    //个人简历
    private String resume;
    //老师照片
    private String pic;
    //是否推荐（1：是 2：否）
    private String isRecommend;
    //get set 方法
    public void setId (String  id){
        this.id=id;
    }
    public  String getId(){
        return this.id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName (String  name){
        this.name=name;
    }
    public  String getName(){
        return this.name;
    }
    public void setIntro (String  intro){
        this.intro=intro;
    }
    public  String getIntro(){
        return this.intro;
    }
    public void setResume (String  resume){
        this.resume=resume;
    }
    public  String getResume(){
        return this.resume;
    }
    public void setPic (String  pic){
        this.pic=pic;
    }
    public  String getPic(){
        return this.pic;
    }
    public void setIsRecommend (String  isRecommend){
        this.isRecommend=isRecommend;
    }
    public  String getIsRecommend(){
        return this.isRecommend;
    }
}
