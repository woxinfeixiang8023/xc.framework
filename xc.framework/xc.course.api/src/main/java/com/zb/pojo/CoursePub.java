package com.zb.pojo;

import java.io.Serializable;

/***
 *
 */
public class CoursePub implements Serializable {
    //主键
    private String id;
    //课程名称
    private String name;
    //一级分类
    private String mt;
    //二级分类
    private String st;
    //课程等级（200001.初级 200002.中级 200003.高级）
    private String grade;
    //学习模式（1.视频 2.文档）
    private String studymodel;
    //课程介绍
    private String description;
    //收费规则，（203001.免费  203002.收费）
    private String charge;
    //有效性，（204001.永久有效  204002.指定时间段）
    private String valid;
    //价格
    private Double price;
    //原价格
    private Double priceOld;
    //过期时间
    private String expires;
    //课程有效期(90天)
    private String expirationTime;
    //课程图片
    private String pic;
    //课程计划
    private String teachplanId;
    //是否热评（1.热评 2.否）
    private Integer isHot;
    //是否最新（1.最新 2.否）
    private Integer isNew;
    //是否推荐（1.推荐 2.否）
    private Integer isRec;
    //是否是精品TOP榜（1.是 2.否）
    private Integer isTop;
    //适用人群
    private String users;
    //学习人数
    private Long stuUsers;
    //课程库存（当前课程特价有多少套）
    private Integer store;
    //是否是特价（根据特价抢购）
    private Integer isDiscount;
    //教师id
    private String teacherId;

    //get set 方法
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getMt() {
        return this.mt;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getSt() {
        return this.st;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setStudymodel(String studymodel) {
        this.studymodel = studymodel;
    }

    public String getStudymodel() {
        return this.studymodel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getCharge() {
        return this.charge;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getValid() {
        return this.valid;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPriceOld(Double priceOld) {
        this.priceOld = priceOld;
    }

    public Double getPriceOld() {
        return this.priceOld;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getExpires() {
        return this.expires;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getExpirationTime() {
        return this.expirationTime;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return this.pic;
    }

    public void setTeachplanId(String teachplanId) {
        this.teachplanId = teachplanId;
    }

    public String getTeachplanId() {
        return this.teachplanId;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsHot() {
        return this.isHot;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsNew() {
        return this.isNew;
    }

    public void setIsRec(Integer isRec) {
        this.isRec = isRec;
    }

    public Integer getIsRec() {
        return this.isRec;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsTop() {
        return this.isTop;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getUsers() {
        return this.users;
    }

    public void setStuUsers(Long stuUsers) {
        this.stuUsers = stuUsers;
    }

    public Long getStuUsers() {
        return this.stuUsers;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getStore() {
        return this.store;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getIsDiscount() {
        return this.isDiscount;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
