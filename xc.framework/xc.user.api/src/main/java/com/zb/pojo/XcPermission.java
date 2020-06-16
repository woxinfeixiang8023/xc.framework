package com.zb.pojo;
import java.io.Serializable;
import java.util.Date;

/***
*   
*/
public class XcPermission implements Serializable {
    //操作许可记录表id
    private String id;
    //角色id 对应xc_role主键
    private String roleId;
    //菜单操作 对应xc_menu主键
    private String menuId;
    //操作时间
    private Date operation_time;
    //get set 方法
    public void setId (String  id){
        this.id=id;
    }
    public  String getId(){
        return this.id;
    }
    public void setRoleId (String  roleId){
        this.roleId=roleId;
    }
    public  String getRoleId(){
        return this.roleId;
    }
    public void setMenuId (String  menuId){
        this.menuId=menuId;
    }
    public  String getMenuId(){
        return this.menuId;
    }
    public void setoperation_time (Date  operation_time){
        this.operation_time=operation_time;
    }
    public  Date getoperation_time(){
        return this.operation_time;
    }
}
