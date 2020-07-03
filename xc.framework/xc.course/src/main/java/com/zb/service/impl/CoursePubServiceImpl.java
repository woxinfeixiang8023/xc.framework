package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.config.DelayRabbitConfig;
import com.zb.config.MQConfig;
import com.zb.dto.Page;
import com.zb.feign.XcUserFeignClient;
import com.zb.form.SearchCoursePubFrom;
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
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageUtil<CoursePub> searchCoursePub(SearchCoursePubFrom searchCoursePubFrom) throws Exception {
        PageUtil<CoursePub> page = new PageUtil<>();
        List<CoursePub> coursePubList = new ArrayList<>();
        //创建查询请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        //构建查询方式
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //分页参数
        searchSourceBuilder.from((searchCoursePubFrom.getIndex() - 1) * searchCoursePubFrom.getSize());
        searchSourceBuilder.size(searchCoursePubFrom.getSize());
        //因为是多个条件的组合创建bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (searchCoursePubFrom.getId() != null && !"".equals(searchCoursePubFrom.getId())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("id", searchCoursePubFrom.getId()));
        }
        // 关键字查询
        if (searchCoursePubFrom.getName() != null && !"".equals(searchCoursePubFrom.getName())) {
            //多个列的分词查询
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(searchCoursePubFrom.getName(), new String[]{"name"})
                    .operator(Operator.OR).field("name", 10));
        }
        if (searchCoursePubFrom.getMt() != null && !"".equals(searchCoursePubFrom.getMt())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("mt", searchCoursePubFrom.getMt()));
        }
        if (searchCoursePubFrom.getSt() != null && !"".equals(searchCoursePubFrom.getSt())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("st", searchCoursePubFrom.getSt()));
        }
        if (searchCoursePubFrom.getGrade() != null && !"".equals(searchCoursePubFrom.getGrade())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("grade", searchCoursePubFrom.getGrade()));
        }
        if (searchCoursePubFrom.getIsHot() != null && searchCoursePubFrom.getIsHot() != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("isHot", searchCoursePubFrom.getIsHot()));
        }
        if (searchCoursePubFrom.getIsNew() != null && searchCoursePubFrom.getIsNew() != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("isNew", searchCoursePubFrom.getIsNew()));
        }
        if (searchCoursePubFrom.getIsRec() != null && searchCoursePubFrom.getIsRec() != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("isRec", searchCoursePubFrom.getIsRec()));
        }
        if (searchCoursePubFrom.getIsTop() != null && searchCoursePubFrom.getIsTop() != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("isTop", searchCoursePubFrom.getIsTop()));
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
        page.setIndex(searchCoursePubFrom.getIndex());
        page.setSize(searchCoursePubFrom.getSize());
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
                Integer storeCount = xcCourseTempStoreMapper.courseStoreCount(storeMap);
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
        String key = "qg:" + currentUser.getId() + ":" + courseId;
        if (redisUtil.hasKey(key)) {
            String json = redisUtil.get(key).toString();
            Map<String, Object> param = JSON.parseObject(json, Map.class);
            if (param.get("state").equals("two")) {
                return "two";
            }
            //抢购成功
            return "success";
        } else {
            //没有库存
            if (stock <= 0) {
                return "none";
            }
            //有库存的情况,没有抢购成功
            return "input";
        }
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
            while (!redisUtil.lock(key)) {
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
            XcCourseTempStore courseTempStoreStatus = xcCourseTempStoreMapper.findCourseTempStoreStatus(param);
            if (courseTempStoreStatus != null) {
                String key1 = "qg:" + currentUser.getId() + ":" + courseId;
                //如果是第二次下单将状态改为two
                param.put("state", "two");
                //重新存储到redis中
                redisUtil.set(key1, JSON.toJSONString(param), 60 * 6);
                System.out.println("同一用户只能抢购一次");
                return;
            }
            //执行购买,添加临时记录
            int i = this.lockCourseStock(courseId, currentUser.getId());
            if (i > 0) {
                //向redis中添加哪个用户的哪个抢购课程信息
                String qgKey = "qg:" + currentUser.getId() + ":" + courseId;
                //用来检测是否是第二次下单
                param.put("state", "one");
                //6分钟之后不支付， 自动清除记录
                redisUtil.set(qgKey, JSON.toJSONString(param), 60 * 6);
                //封装发送延迟队列的数据
                Map<String, Object> mqData = new HashMap<>();
                mqData.put("courseId", courseId);
                mqData.put("userId", currentUser.getId());
                System.out.println("延迟队列 6分钟之后检查库存订单的状态 ");
                amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_EXCHANGE_NAME, DelayRabbitConfig.ORDER_ROUTING_KEY, mqData, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setExpiration(60 * 1000 * 6 + "");
                        return message;
                    }
                });
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

    @RabbitListener(queues = DelayRabbitConfig.ORDER_DELAY_QUEUE)
    @Override
    public void recoverOrderMessage(Map<String, Object> param, Message message, Channel channel) {
        try {
            System.out.println("六分钟之后获取到临时库存的状态信息");
            String courseId = param.get("courseId").toString();
            String userId = param.get("userId").toString();
            param.put("status", 1);
            System.out.println("courseId:" + courseId + "\t" + "userId:" + userId);
            System.out.println("1.修改临时库存的状态");
            //查询商品的状态是否为0 执行以下操作
            XcCourseTempStore courseTempStoreStatus = xcCourseTempStoreMapper.findCourseTempStoreStatus(param);
            if (courseTempStoreStatus.getStatus() == 0) {
                //修改数据库的商品状态
                Integer num = xcCourseTempStoreMapper.updateXcCourseTempStore(param);
                if (num > 0) {
                    System.out.println("回滚库存......");
                    //获取redis中的库存信息
                    String key = "discountCourse:" + courseId;
                    String json = redisUtil.get(key).toString();
                    CoursePub coursePub = JSON.parseObject(json, CoursePub.class);
                    coursePub.setStore(coursePub.getStore() + 1);
                    //重新写入到redis中
                    redisUtil.set(key, JSON.toJSONString(coursePub));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CoursePub getCoursePubById(String id) {
        try {
            return coursePubMapper.getCoursePubById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CoursePub> getCoursePubListByMap(Map<String, Object> param) {
        try {
            return coursePubMapper.getCoursePubListByMap(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getCoursePubCountByMap(Map<String, Object> param) {
        try {
            return coursePubMapper.getCoursePubCountByMap(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insertCoursePub(CoursePub coursePub) {
        try {
            return coursePubMapper.insertCoursePub(coursePub);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateCoursePub(CoursePub coursePub) {
        try {
            return coursePubMapper.updateCoursePub(coursePub);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delCoursePubById(String id) {
        try {
            return coursePubMapper.delCoursePubById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
