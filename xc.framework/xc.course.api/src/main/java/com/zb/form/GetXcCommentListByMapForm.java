package com.zb.form;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/24
 * @Version V1.0
 */
public class GetXcCommentListByMapForm {
    private String courseId;
    private Integer index;
    private Integer size;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
