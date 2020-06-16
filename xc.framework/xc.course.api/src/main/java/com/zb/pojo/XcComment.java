package com.zb.pojo;

import java.io.Serializable;

/***
 *
 */
public class XcComment implements Serializable {
    //
    private Long id;
    //课程id
    private String courseId;
    //评论内容
    private String commentContext;
    //课程评分
    private Double score;
    //用户id
    private String userId;
    //评论日期
    private String createDate;
    //用户昵称
    private String name;
    //用户头像
    private String userpic;
    //平均分
    private Double avgScore;
    //总评分
    private Double sumScore;

    //get set 方法
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }

    public String getCommentContext() {
        return this.commentContext;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getScore() {
        return this.score;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Double getSumScore() {
        return sumScore;
    }

    public void setSumScore(Double sumScore) {
        this.sumScore = sumScore;
    }
}
