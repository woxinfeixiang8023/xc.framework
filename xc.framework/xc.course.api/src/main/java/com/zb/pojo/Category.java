package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class Category implements Serializable {
    //主键
    private String id;
    //分类名称
    private String name;
    //分类标签默认和名称一样
    private String label;
    //父结点id
    private String parentid;
    //是否显示
    private String isshow;
    //排序字段
    private Integer orderby;
    //是否叶子
    private String isleaf;
//get set 方法
    public void setId (String  id){
    this.id=id;
    }
    public  String getId(){
    return this.id;
    }
    public void setName (String  name){
    this.name=name;
    }
    public  String getName(){
    return this.name;
    }
    public void setLabel (String  label){
    this.label=label;
    }
    public  String getLabel(){
    return this.label;
    }
    public void setParentid (String  parentid){
    this.parentid=parentid;
    }
    public  String getParentid(){
    return this.parentid;
    }
    public void setIsshow (String  isshow){
    this.isshow=isshow;
    }
    public  String getIsshow(){
    return this.isshow;
    }
    public void setOrderby (Integer  orderby){
    this.orderby=orderby;
    }
    public  Integer getOrderby(){
    return this.orderby;
    }
    public void setIsleaf (String  isleaf){
    this.isleaf=isleaf;
    }
    public  String getIsleaf(){
    return this.isleaf;
    }
}
