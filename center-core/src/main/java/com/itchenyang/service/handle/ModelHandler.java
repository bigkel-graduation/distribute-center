package com.itchenyang.service.handle;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ModelHandler {
    @Resource
    private Environment env;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    public List<Map<String, String>> getCountByModelType(long startTime, long endTime) {
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("modelTypeCount")
                .field("modelType.keyword");
        try {
            SearchRequest request = new SearchRequest()
                    .indices(env.getProperty("model"))
                    .source(new SearchSourceBuilder()
                            .size(0)
                            .query(QueryBuilders.rangeQuery("requestTime")
                                    .timeZone("+08:00")
                                    .gte(startTime)
                                    .lt(endTime))
                            .aggregation(aggregation));
            SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
            Terms modelTypeCount = searchResponse.getAggregations().get("modelTypeCount");
            List<Map<String, String>> maps = new ArrayList<>();
            for (Terms.Bucket bucket : modelTypeCount.getBuckets()) {
                Map<String, String> map = new HashMap<>();
                map.put("type", bucket.getKeyAsString());
                map.put("count", bucket.getDocCount() + "");
                maps.add(map);
            }
            return maps;
        } catch (Exception e) {
            log.info("服务类型统计查询es异常: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, String>> getCountByModelName(long startTime, long endTime) {
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("modelTypeCount")
                .field("modelType.keyword")
                .subAggregation(AggregationBuilders.terms("modelNameCount")
                        .field("modelName.keyword"));
        try {
            SearchRequest request = new SearchRequest()
                    .indices(env.getProperty("model"))
                    .source(new SearchSourceBuilder()
                            .size(0)
                            .query(QueryBuilders.rangeQuery("requestTime")
                                    .timeZone("+08:00")
                                    .gte(startTime)
                                    .lt(endTime))
                            .aggregation(aggregation));
            SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
            Terms modelTypeCount = searchResponse.getAggregations().get("modelTypeCount");
            List<Map<String, String>> maps = new ArrayList<>();
            for (Terms.Bucket TypeBucket : modelTypeCount.getBuckets()) {
                Terms modelNameCount = TypeBucket.getAggregations().get("modelNameCount");
                for (Terms.Bucket NameBucket : modelNameCount.getBuckets()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("type", TypeBucket.getKeyAsString());
                    map.put("name", NameBucket.getKeyAsString());
                    map.put("count", NameBucket.getDocCount() + "");
                    maps.add(map);
                }
            }
            return maps;
        } catch (Exception e) {
            log.info("服务名称统计查询es异常: {}", e.getMessage());
            return new ArrayList<>();
        }
    }
}
