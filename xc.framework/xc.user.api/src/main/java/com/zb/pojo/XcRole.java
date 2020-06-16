package com.zb.pojo;
import java.io.Serializable;
import java.util.Date;

/***
*   
*/
public class XcRole implements Serializable {
    //角色表id
    private String id;
    //角色名
    private String roleName;
    //角色代表
    private String roleCode;
    //
    private Date createTime;
    //
    private Date updateTime;
    //
    private String status;
    //get set 方法
    public void setId (String  id){
        this.id=id;
    }
    public  String getId(){
        return this.id;
    }
    public void setRoleName (String  roleName){
        this.roleName=roleName;
    }
    public  String getRoleName(){
        return this.roleName;
    }
    public void setRoleCode (String  roleCode){
        this.roleCode=roleCode;
    }
    public  String getRoleCode(){
        return this.roleCode;
    }
    public void setCreateTime (Date  createTime){
        this.createTime=createTime;
    }
    public  Date getCreateTime(){
        return this.createTime;
    }
    public void setUpdateTime (Date  updateTime){
        this.updateTime=updateTime;
    }
    public  Date getUpdateTime(){
        return this.updateTime;
    }
    public void setStatus (String  status){
        this.status=status;
    }
    public  String getStatus(){
        return this.status;
    }
}
