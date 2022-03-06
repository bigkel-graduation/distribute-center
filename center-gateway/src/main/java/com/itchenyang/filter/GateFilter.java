package com.itchenyang.filter;

import com.alibaba.fastjson.JSON;
import com.itchenyang.entity.ModelMessage;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class GateFilter implements GlobalFilter, Ordered {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Resource
    private Environment env;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            String path = exchange.getRequest().getPath().toString();
            log.info("访问当前的资源的路径为: {}",path);
            // 将请求模块的path转换成具体信息的实体类
            List<ModelMessage> modelLists = new ArrayList<>();
            if (!StringUtils.isEmpty(path)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat();
                dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
                String[] model = path.split("/");
                ModelMessage modelMessage = new ModelMessage();
                modelMessage.setModelType(model[2]);
                modelMessage.setModelName(model[3]);
                modelMessage.setRequestTime(dateFormat.format(new Date()));
                modelLists.add(modelMessage);
            }

            final String INDEX = env.getProperty("model");
            if (findIndex(INDEX)) {
                // 往es中写入值
                insertAllDoc(INDEX,modelLists);
            }else {
                // 创建索引后，往es中写入值
                createIndex(INDEX);
                insertAllDoc(INDEX,modelLists);
            }
        } catch (Exception e) {
            log.info("写入es失败: {}", e.getMessage());
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public Boolean findIndex(String INDEX) {
        try {
            GetIndexRequest request = new GetIndexRequest(INDEX);
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("查询索引出错: {}",e.getMessage());
            return false;
        }
    }

    public void createIndex(String INDEX) {
        try {
            CreateIndexRequest request = new CreateIndexRequest(INDEX);
            client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("创建索引失败: {}",e.getMessage());
        }
    }

    public void insertAllDoc(String INDEX, List<ModelMessage> modelLists) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            for (ModelMessage modelList : modelLists) {
                bulkRequest.add(new IndexRequest(INDEX)
                        .id(modelList.getIndexId())
                        .source(JSON.toJSONString(modelList), XContentType.JSON));
            }
            BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (response.hasFailures()) {
                log.info("写入es失败!");
            }else {
                log.info("写入es成功!");
            }
        } catch (IOException e) {
            log.info("写入es失败: {}", e.getMessage());
        }
    }
}
