package com.zb.form;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public class TeachplanForm {
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
    //适用人群
    private String users;
    //学习人数
    private Long stuUsers;
    //时长，单位分钟
    private Double timelength;
    //教师称呼
    private String teacherName;
    //教师简介
    private String intro;
    //教师简历
    private String resume;
    //教师照片
    private String teacherPic;

    public TeachplanForm() {
    }

    public TeachplanForm(String id, String name, String mt, String st, String grade, String studymodel, String description, String charge, String valid, Double price, Double priceOld, String expires, String expirationTime, String pic, String users, Long stuUsers, Double timelength, String teacherName, String intro, String resume, String teacherPic) {
        this.id = id;
        this.name = name;
        this.mt = mt;
        this.st = st;
        this.grade = grade;
        this.studymodel = studymodel;
        this.description = description;
        this.charge = charge;
        this.valid = valid;
        this.price = price;
        this.priceOld = priceOld;
        this.expires = expires;
        this.expirationTime = expirationTime;
        this.pic = pic;
        this.users = users;
        this.stuUsers = stuUsers;
        this.timelength = timelength;
        this.teacherName = teacherName;
        this.intro = intro;
        this.resume = resume;
        this.teacherPic = teacherPic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudymodel() {
        return studymodel;
    }

    public void setStudymodel(String studymodel) {
        this.studymodel = studymodel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Double priceOld) {
        this.priceOld = priceOld;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Long getStuUsers() {
        return stuUsers;
    }

    public void setStuUsers(Long stuUsers) {
        this.stuUsers = stuUsers;
    }

    public Double getTimelength() {
        return timelength;
    }

    public void setTimelength(Double timelength) {
        this.timelength = timelength;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getTeacherPic() {
        return teacherPic;
    }

    public void setTeacherPic(String teacherPic) {
        this.teacherPic = teacherPic;
    }
}
