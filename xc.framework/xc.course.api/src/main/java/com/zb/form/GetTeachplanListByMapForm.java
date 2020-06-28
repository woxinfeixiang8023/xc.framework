package com.zb.form;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/24
 * @Version V1.0
 */
public class GetTeachplanListByMapForm {
    private String courseId;
    private String grade;
    private String parentId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
