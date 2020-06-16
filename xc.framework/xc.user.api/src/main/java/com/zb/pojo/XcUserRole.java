package com.zb.pojo;
import java.io.Serializable;
import java.util.Date;

/***
*   
*/
public class XcUserRole implements Serializable {
    //用户角色表id
    private String id;
    //用户id对应xc_user主键
    private String userId;
    //用户角色id对应xc_role主键
    private String roleId;
    //用户角色创建时间
    private Date createTime;
    //描述
    private String creator;
    //get set 方法
    public void setId (String  id){
        this.id=id;
    }
    public  String getId(){
        return this.id;
    }
    public void setUserId (String  userId){
        this.userId=userId;
    }
    public  String getUserId(){
        return this.userId;
    }
    public void setRoleId (String  roleId){
        this.roleId=roleId;
    }
    public  String getRoleId(){
        return this.roleId;
    }
    public void setCreateTime (Date  createTime){
        this.createTime=createTime;
    }
    public  Date getCreateTime(){
        return this.createTime;
    }
    public void setCreator (String  creator){
        this.creator=creator;
    }
    public  String getCreator(){
        return this.creator;
    }
}
