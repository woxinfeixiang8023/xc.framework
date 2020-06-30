package com.zb.form;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/30
 * @Version V1.0
 */
public class CartForm {
    private Integer uid;
    private Integer goodsId;
    private Integer num;
    private String op;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
