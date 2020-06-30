package com.zb.service;

import com.zb.pojo.CoursePubCart;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/30
 * @Version V1.0
 */
public interface CoursePubCartService {

    public void dbToRedisCart();

    public boolean addCart(Integer uid, String goodsId, Integer num);

    public void updateNum(Integer uid, String goodsId, Integer num, String op);

    public void delItem(Integer uid, String goodsId);

    public CoursePubCart getCartAllItem(Integer uid);
}
