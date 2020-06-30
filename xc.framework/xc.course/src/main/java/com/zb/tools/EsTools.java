package com.zb.tools;


import com.zb.mapper.CoursePubMapper;
import com.zb.pojo.CoursePub;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class EsTools {

    @Autowired
    private RestHighLevelClient client;

    @Autowired(required = false)
    private CoursePubMapper coursePubMapper;

    public void addIndex() throws Exception {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("xc_course");
        //参数
        createIndexRequest.settings(
                Settings.builder().put("number_of_shards", 1).
                        put("number_of_replicas", 0).
                        build());
        //创建映射
        createIndexRequest.mapping("doc", "{\n" +
                "\t\"properties\": {\n" +
                "\t\t\"id\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"name\": {\n" +
                "\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\"analyzer\": \"ik_max_word\",\n" +
                "\t\t\t\"search_analyzer\": \"ik_smart\"\n" +
                "\t\t},\n" +
                "\t\t\"mt\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"st\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"grade\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"studymodel\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"description\": {\n" +
                "\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\"analyzer\": \"ik_max_word\",\n" +
                "\t\t\t\"search_analyzer\": \"ik_smart\"\n" +
                "\t\t},\n" +
                "\t\t\"charge\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"valid\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"price\": {\n" +
                "\t\t\t\"type\": \"float\"\n" +
                "\t\t},\n" +
                "\t\t\"priceOld\": {\n" +
                "\t\t\t\"type\": \"float\"\n" +
                "\t\t},\n" +
                "\t\t\"expires\": {\n" +
                "\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\"index\": false\n" +
                "\t\t},\n" +
                "\t\t\"expirationTime\": {\n" +
                "\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\"index\": false\n" +
                "\t\t},\n" +
                "\t\t\"pic\": {\n" +
                "\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\"index\": false\n" +
                "\t\t},\n" +
                "\t\t\"teachplanId\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"isHot\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"isNew\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"isRec\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"isTop\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"users\": {\n" +
                "\t\t\t\"type\": \"text\"\n" +
                "\t\t},\n" +
                "\t\t\"stuUsers\": {\n" +
                "\t\t\t\"type\": \"keyword\",\n" +
                "\t\t\t\"index\": false\n" +
                "\t\t},\n" +
                "\t\t\"store\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"isDiscount\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"teacherId\": {\n" +
                "\t\t\t\"type\": \"keyword\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}", XContentType.JSON);
        //获取索引对象
        IndicesClient indices = client.indices();
        //创建并返回一个响应对象
        CreateIndexResponse indexResponse = indices.create(createIndexRequest);
        //是否执行成功
        boolean val = indexResponse.isAcknowledged();
        System.out.println(val);
    }

    public void importData() {
        try {
            List<CoursePub> coursePubs = coursePubMapper.importData();

            for (CoursePub coursePub : coursePubs) {
                Map<String, Object> data = new HashMap<>();
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
                //创建请求对象
                IndexRequest indexRequest = new IndexRequest("xc_course", "doc", coursePub.getId() + "");
                //绑定数据
                indexRequest.source(data);
                //执行获取响应
                IndexResponse indexResponse = client.index(indexRequest);
                DocWriteResponse.Result result = indexResponse.getResult();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
