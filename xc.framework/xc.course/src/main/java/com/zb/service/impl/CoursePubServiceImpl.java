package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.config.MQConfig;
import com.zb.dto.Page;
import com.zb.feign.XcUserFeignClient;
import com.zb.mapper.CoursePubMapper;
import com.zb.mapper.XcCourseTempStoreMapper;
import com.zb.pojo.CoursePub;
import com.zb.pojo.XcCourseTempStore;
import com.zb.pojo.XcUser;
import com.zb.service.CoursePubService;
import com.zb.util.IdWorker;
import com.zb.util.PageUtil;
import com.zb.util.RedisUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/6
 * @Version V1.0
 */
@Service
public class CoursePubServiceImpl implements CoursePubService {

    @Autowired(required = false)
    private RestHighLevelClient client;
    @Autowired(required = false)
    private CoursePubMapper coursePubMapper;
    @Autowired(required = false)
    private XcCourseTempStoreMapper xcCourseTempStoreMapper;
    @Autowired
    private XcUserFeignClient xcUserFeignClient;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public PageUtil<CoursePub> searchCoursePub(String id, String name, String mt, String st, String grade, Integer isHot, Integer isNew, Integer isRec, Integer isTop, Integer index, Integer size) throws Exception {
        PageUtil<CoursePub> page = new PageUtil<>();
        List<CoursePub> coursePubList = new ArrayList<>();
        //创建查询请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        //构建查询方式
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //分页参数
        searchSourceBuilder.from((index - 1) * size);
        searchSourceBuilder.size(size);
        //因为是多个条件的组合创建bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (id != null && !"".equals(id)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("id", id));
        }
        // 关键字查询
        if (name != null && !"".equals(name)) {
            //多个列的分词查询
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(name, new String[]{"name"})
                    .operator(Operator.OR).field("name", 10));
        }
        if (mt != null && !"".equals(mt)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("mt", mt));
        }
        if (st != null && !"".equals(st)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("st", st));
        }
        if (grade != null && !"".equals(grade)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("grade", grade));
        }
        if (isHot != null && isHot != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("isHot", isHot));
        }
        if (isNew != null && isNew != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("isNew", isNew));
        }
        if (isRec != null && isRec != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("isRec", isRec));
        }
        if (isTop != null && isTop != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("isTop", isTop));
        }
        //添加排序
        searchSourceBuilder.sort(new FieldSortBuilder("stuUsers").order(SortOrder.ASC));
        //创建高亮对象
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置标签
        highlightBuilder.preTags("<div style='color:red;'>");
        highlightBuilder.postTags("</div>");
        //添加高亮字段
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        //将highlightBuilder对象添加到查询对象中
        searchSourceBuilder.highlighter(highlightBuilder);
        //绑定查询构建对象
        searchSourceBuilder.query(boolQueryBuilder);
        //将构建对象存储到request对象中去
        searchRequest.source(searchSourceBuilder);
        //执行查询并获取响应对象
        SearchResponse searchResponse = client.search(searchRequest);
        //获取索引的hits
        SearchHits hits = searchResponse.getHits();


        //获取到数组对象(存储的数据)
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit : hitsHits) {
//            String id = hit.getId();
            //获取原始数据到map数据
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String id1 = sourceAsMap.get("id").toString();
            String cname = sourceAsMap.get("name").toString();
            String mt1 = sourceAsMap.get("mt").toString();
            String st1 = sourceAsMap.get("st").toString();
            String grade1 = sourceAsMap.get("grade").toString();
            String studymodel = sourceAsMap.get("studymodel").toString();
            String description = sourceAsMap.get("description").toString();
            String charge = sourceAsMap.get("charge").toString();
            String valid = sourceAsMap.get("valid").toString();
            Double price = Double.parseDouble(sourceAsMap.get("price").toString());
            Double priceOld = Double.parseDouble(sourceAsMap.get("priceOld").toString());
            String expires = sourceAsMap.get("expires").toString();
            String expirationTime = sourceAsMap.get("expirationTime").toString();
            String pic = sourceAsMap.get("pic").toString();
            String teachplanId = sourceAsMap.get("teachplanId").toString();
            Integer isHot1 = Integer.parseInt(sourceAsMap.get("isHot").toString());
            Integer isNew1 = Integer.parseInt(sourceAsMap.get("isNew").toString());
            Integer isRec1 = Integer.parseInt(sourceAsMap.get("isRec").toString());
            Integer isTop1 = Integer.parseInt(sourceAsMap.get("isTop").toString());
            String users = sourceAsMap.get("users").toString();
            Long stuUsers = Long.parseLong(sourceAsMap.get("stuUsers").toString());
            Integer store = Integer.parseInt(sourceAsMap.get("isRec").toString());
            Integer isDiscount = Integer.parseInt(sourceAsMap.get("isTop").toString());

            //获取高亮数据
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields != null) {
                HighlightField nameField = highlightFields.get("name");
                //验证高亮部分中是否包含该属性
                if (nameField != null) {
                    Text[] nameText = nameField.getFragments();
                    StringBuffer nameSbf = new StringBuffer();
                    for (Text text : nameText) {
                        nameSbf.append(text);
                    }
                    //覆盖掉原始的数据
                    cname = nameSbf.toString();
                }
            }
            CoursePub coursePub = new CoursePub();
            coursePub.setId(id1);
            coursePub.setName(cname);
            coursePub.setMt(mt1);
            coursePub.setSt(st1);
            coursePub.setGrade(grade1);
            coursePub.setStudymodel(studymodel);
            coursePub.setDescription(description);
            coursePub.setCharge(charge);
            coursePub.setValid(valid);
            coursePub.setPrice(price);
            coursePub.setPriceOld(priceOld);
            coursePub.setExpires(expires);
            coursePub.setExpirationTime(expirationTime);
            coursePub.setPic(pic);
            coursePub.setTeachplanId(teachplanId);
            coursePub.setIsHot(isHot1);
            coursePub.setIsNew(isNew1);
            coursePub.setIsRec(isRec1);
            coursePub.setIsTop(isTop1);
            coursePub.setUsers(users);
            coursePub.setStuUsers(stuUsers);
            coursePub.setStore(store);
            coursePub.setIsDiscount(isDiscount);
            coursePubList.add(coursePub);
        }
        page.setData(coursePubList);
        page.setIndex(index);
        page.setSize(size);
        page.setCount(Integer.parseInt(hits.getTotalHits() + ""));
        return page;
    }

    @Scheduled(cron = "0 0 12 * * *")
    @Override
    public void coursePubToRedis() {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("isDiscount", 1);
            List<CoursePub> coursePubListByMap = coursePubMapper.getCoursePubListByMap(param);
            for (CoursePub coursePub : coursePubListByMap) {
                //根据商品编号查询已预定的count
                Map<String, Object> storeMap = new HashMap<>();
                storeMap.put("courseId", coursePub.getId());
                Integer storeCount = xcCourseTempStoreMapper.getXcCourseTempStoreCountByMap(param);
                //原始库存-已预定count=实际可以预定的库存
                coursePub.setStore(coursePub.getStore() - storeCount);
                //写入到redis中
                redisUtil.set("discountCourse:" + coursePub.getId(), JSON.toJSONString(coursePub));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String qgCourse(String courseId, String token) {
        Map<String, Object> param = new HashMap<>();
        param.put("courseId", courseId);
        param.put("token", token);
        amqpTemplate.convertAndSend(MQConfig.xcExchange, "inform.order", param);
        return "正在排队中，请等待...";
    }

    @Override
    public String qgWhile(String courseId, String token) {
        //根据token获取用户信息
        XcUser currentUser = xcUserFeignClient.getCurrentUser(token);
        //查询库存
        int stock = this.checkRoomStock(courseId);
        //有库存等待
        //去redis中检查数据
        String key = "qg:" + currentUser.getId() + courseId;
        if (redisUtil.hasKey(key)) {

        }
        return null;
    }

    @Override
    public int checkRoomStock(String courseId) {
        String key = "discountCourse:" + courseId;
        if (redisUtil.hasKey(key)) {
            String json = redisUtil.get(key).toString();
            CoursePub coursePub = JSON.parseObject(json, CoursePub.class);
            if (coursePub.getStore() > 0) {
                return 1;
            }
        }
        return 0;
    }

    @RabbitListener(queues = MQConfig.xcQueue)
    @Override
    public void reviceQg(Map<String, Object> param, Message message, Channel channel) {
        String courseId = param.get("courseId").toString();
        String token = param.get("token").toString();
        System.out.println("courseId:" + courseId + "\t" + "token:" + token);
        //1.获取用户的信息
        //判断用户是否为空
        XcUser currentUser = xcUserFeignClient.getCurrentUser(token);
        if (currentUser == null) {
            return;
        }
        //使用自定义的分布式锁 ，完成高并发下的抢购操作
        String key = "lock:" + courseId;
        try {
            //循环获取锁
            //自旋
            while (!redisUtil.hasKey(key)) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //2.检查库存
            int stock = this.checkRoomStock(courseId);
            if (stock <= 0) {
                return;
            }
            //3.用户是否是第二次下单
            Map<String, Object> one = new HashMap<>();
            one.put("courseId", courseId);
            one.put("userId", currentUser.getId());
            List<XcCourseTempStore> xcCourseTempStoreListByMap = xcCourseTempStoreMapper.getXcCourseTempStoreListByMap(param);
            if (xcCourseTempStoreListByMap != null) {
                String key1 = "qg:" + currentUser.getId() + ":" + courseId;
                //如果是第二次下单将状态改为two
                param.put("state", "two");
                //重新存储到redis中
                redisUtil.set(key1, JSON.toJSONString(param), 60 * 5);
                System.out.println("同一用户只能抢购一次");
                return;
            }
            //执行购买
            int i = this.lockCourseStock(courseId, currentUser.getId());
            if (i > 0) {
                //向redis中添加哪个用户的哪个抢购课程信息

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisUtil.unlock(key);
        }

    }

    @Override
    public int lockCourseStock(String courseId, String uid) {
        try {
            //获取数据
            String key = "discountCourse:" + courseId;
            String json = redisUtil.get(key).toString();
            CoursePub coursePub = JSON.parseObject(json, CoursePub.class);
            //创建临时库存记录
            XcCourseTempStore xcCourseTempStore = new XcCourseTempStore();
            xcCourseTempStore.setId(Long.parseLong(IdWorker.getId()));
            xcCourseTempStore.setCourseId(courseId);
            xcCourseTempStore.setStore(coursePub.getStore());
            xcCourseTempStore.setStatus(0);
            xcCourseTempStore.setCreatedBy(uid);
            //修改库存
            coursePub.setStore(coursePub.getStore() - 1);
            //重新写入到redis中
            redisUtil.set(key, JSON.toJSONString(coursePub));
            //执行添加临时库存数据
            return xcCourseTempStoreMapper.insertXcCourseTempStore(xcCourseTempStore);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
