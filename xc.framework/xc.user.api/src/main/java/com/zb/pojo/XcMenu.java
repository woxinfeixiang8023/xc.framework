package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class XcMenu implements Serializable {
    //操作编号id
    private String id;
    //菜单编码
    private String code;
    //父菜单ID
    private String pId;
    //菜单名称
    private String menuName;
    //是否是菜单（1：是 0：否）
    private String isMenu;
    //菜单层级
    private Integer level;
    //菜单排序
    private Integer sort;
    //状态
    private String status;
    //图标
    private String icon;
    //get set 方法
    public void setId (String  id){
        this.id=id;
    }
    public  String getId(){
        return this.id;
    }
    public void setCode (String  code){
        this.code=code;
    }
    public  String getCode(){
        return this.code;
    }
    public void setPId (String  pId){
        this.pId=pId;
    }
    public  String getPId(){
        return this.pId;
    }
    public void setMenuName (String  menuName){
        this.menuName=menuName;
    }
    public  String getMenuName(){
        return this.menuName;
    }
    public void setIsMenu (String  isMenu){
        this.isMenu=isMenu;
    }
    public  String getIsMenu(){
        return this.isMenu;
    }
    public void setLevel (Integer  level){
        this.level=level;
    }
    public  Integer getLevel(){
        return this.level;
    }
    public void setSort (Integer  sort){
        this.sort=sort;
    }
    public  Integer getSort(){
        return this.sort;
    }
    public void setStatus (String  status){
        this.status=status;
    }
    public  String getStatus(){
        return this.status;
    }
    public void setIcon (String  icon){
        this.icon=icon;
    }
    public  String getIcon(){
        return this.icon;
    }
}
