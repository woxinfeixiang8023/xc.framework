package com.zb.service.impl;

import com.zb.mapper.CoursePubMapper;
import com.zb.pojo.CoursePub;
import com.zb.pojo.CoursePubCart;
import com.zb.service.CoursePubCartService;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/30
 * @Version V1.0
 */
@Service
public class CoursePubCartServiceImpl implements CoursePubCartService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired(required = false)
    private CoursePubMapper coursePubMapper;

    /**
     * 所有商品存入到redis中
     */
    @Override
    public void dbToRedisCart() {
        try {
            Map<String, Object> data = new HashMap<>();
            List<CoursePub> coursePubListByMap = coursePubMapper.getCoursePubListByMap(data);
            for (CoursePub coursePub : coursePubListByMap) {
                data.put("id", coursePub.getId());
                data.put("name", coursePub.getName());
                data.put("mt", coursePub.getMt());
                data.put("st", coursePub.getSt());
                data.put("grade", coursePub.getGrade());
                data.put("studymodel", coursePub.getStudymodel());
                data.put("description", coursePub.getDescription());
                data.put("charge", coursePub.getCharge());
                data.put("valid", coursePub.getValid());
                data.put("price", coursePub.getPrice());
                data.put("priceOld", coursePub.getPriceOld());
                data.put("expires", coursePub.getExpires());
                data.put("expirationTime", coursePub.getExpirationTime());
                data.put("pic", coursePub.getPic());
                data.put("teachplanId", coursePub.getTeachplanId());
                data.put("isHot", coursePub.getIsHot());
                data.put("isNew", coursePub.getIsNew());
                data.put("isRec", coursePub.getIsRec());
                data.put("isTop", coursePub.getIsTop());
                data.put("users", coursePub.getUsers());
                data.put("stuUsers", coursePub.getStuUsers());
                data.put("store", coursePub.getStore());
                data.put("isDiscount", coursePub.getIsDiscount());
                data.put("teacherId", coursePub.getTeacherId());
                redisUtil.hmset("goods:" + coursePub.getId(), data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加购物车
     *
     * @param uid
     * @param goodsId
     * @param num
     * @return
     */
    @Override
    public boolean addCart(Integer uid, String goodsId, Integer num) {
        Map<String, Object> data = new HashMap<>();
        data.put(goodsId + "", num);
        return redisUtil.hmset("cart:" + uid, data);
    }

    /**
     * 修改数量
     *
     * @param uid
     * @param goodsId
     * @param num
     * @param op
     */
    @Override
    public void updateNum(Integer uid, String goodsId, Integer num, String op) {
        if (op.equals("add")) {
            redisUtil.hincr("cart:" + uid, goodsId + "", num);
        } else {
            redisUtil.hdecr("cart:" + uid, goodsId + "", num);
        }
    }

    /**
     * 删除购物车的选项
     *
     * @param uid
     * @param goodsId
     */
    @Override
    public void delItem(Integer uid, String goodsId) {
        redisUtil.hdel("cart:" + uid, goodsId + "");
    }

    @Override
    public CoursePubCart getCartAllItem(Integer uid) {
        CoursePubCart cart = new CoursePubCart();
        List<CoursePub> items = new ArrayList<>();
        Map<Object, Object> allItem = redisUtil.getAll("cart:" + uid);
        Iterator<Object> it = allItem.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next(); //商品编号
            Object val = allItem.get(key);//商品数量
            System.out.println(key + "\t" + val);
            CoursePub item = new CoursePub();
            item.setId(key.toString());
            item.setNum(Integer.parseInt(val.toString()));
            String goodsKey = "goods:" + item.getId();
            Map<Object, Object> hmget = redisUtil.hmget(goodsKey);
            item.setName(hmget.get("name").toString());
            item.setMt(hmget.get("mt").toString());
            item.setSt(hmget.get("st").toString());
            item.setGrade(hmget.get("grade").toString());
            item.setStudymodel(hmget.get("studymodel").toString());
            item.setDescription(hmget.get("description").toString());
            item.setCharge(hmget.get("charge").toString());
            item.setValid(hmget.get("valid").toString());
            item.setPrice(Double.parseDouble(hmget.get("price").toString()) * Integer.parseInt(val.toString()));
            item.setPriceOld(Double.parseDouble(hmget.get("priceOld").toString()));
            item.setExpires(hmget.get("expires").toString());
            item.setExpirationTime(hmget.get("expirationTime").toString());
            item.setPic(hmget.get("pic").toString());
            item.setTeachplanId(hmget.get("teachplanId").toString());
            item.setIsHot(Integer.parseInt(hmget.get("isHot").toString()));
            item.setIsNew(Integer.parseInt(hmget.get("isNew").toString()));
            item.setIsRec(Integer.parseInt(hmget.get("isRec").toString()));
            item.setIsTop(Integer.parseInt(hmget.get("isTop").toString()));
            item.setUsers(hmget.get("users").toString());
            item.setStuUsers(Long.parseLong(hmget.get("stuUsers").toString()));
            item.setStore(Integer.parseInt(hmget.get("store").toString()));
            item.setIsDiscount(Integer.parseInt(hmget.get("isDiscount").toString()));
            item.setTeacherId(hmget.get("teacherId").toString());
            items.add(item);
        }
        cart.setItems(items);
        return cart;
    }
}
