package com.zb.pojo;
import java.io.Serializable;
import java.util.Date;

/***
*   
*/
public class XcUser implements Serializable {
    //用户id
    private String id;
    //用户名
    private String username;
    //用户密码
    private String password;
    //姓名
    private String name;
    //头像
    private String userpic;
    //101001：学生 101002：老师 101003：管理员
    private String utype;
    //生日
    private String birthday;
    //1074837115@qq.com
    private String sex;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //qq
    private String qq;
    //用户状态
    private String status;
    //用户创建时间
    private String createTime;
    //用户更新资料时间
    private String updateTime;
    //get set 方法
    public void setId (String  id){
        this.id=id;
    }
    public  String getId(){
        return this.id;
    }
    public void setUsername (String  username){
        this.username=username;
    }
    public  String getUsername(){
        return this.username;
    }
    public void setPassword (String  password){
        this.password=password;
    }
    public  String getPassword(){
        return this.password;
    }
    public void setName (String  name){
        this.name=name;
    }
    public  String getName(){
        return this.name;
    }
    public void setUserpic (String  userpic){
        this.userpic=userpic;
    }
    public  String getUserpic(){
        return this.userpic;
    }
    public void setUtype (String  utype){
        this.utype=utype;
    }
    public  String getUtype(){
        return this.utype;
    }
    public void setBirthday (String  birthday){
        this.birthday=birthday;
    }
    public  String getBirthday(){
        return this.birthday;
    }
    public void setSex (String  sex){
        this.sex=sex;
    }
    public  String getSex(){
        return this.sex;
    }
    public void setEmail (String  email){
        this.email=email;
    }
    public  String getEmail(){
        return this.email;
    }
    public void setPhone (String  phone){
        this.phone=phone;
    }
    public  String getPhone(){
        return this.phone;
    }
    public void setQq (String  qq){
        this.qq=qq;
    }
    public  String getQq(){
        return this.qq;
    }
    public void setStatus (String  status){
        this.status=status;
    }
    public  String getStatus(){
        return this.status;
    }
    public void setCreateTime (String  createTime){
        this.createTime=createTime;
    }
    public  String getCreateTime(){
        return this.createTime;
    }
    public void setUpdateTime (String  updateTime){
        this.updateTime=updateTime;
    }
    public  String getUpdateTime(){
        return this.updateTime;
    }
}
