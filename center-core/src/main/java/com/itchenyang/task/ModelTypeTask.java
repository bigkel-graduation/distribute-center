package com.itchenyang.task;

import com.itchenyang.service.TrendManageService;
import com.itchenyang.service.handle.ModelHandler;
import com.itchenyang.time.EarlyMorningTime;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ModelTypeTask implements Job{
    @Resource
    private ModelHandler modelHandler;

    @Resource
    private TrendManageService trendManageService;

    private final static String FLAG = "t_model_type";

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("==============开始统计服务类型的数据量==============");
        // 时间范围
        Pair<Long, Long> time = new EarlyMorningTime().getTime();
        long startTimeTamp = time.getLeft();
        long endTimeTamp = time.getRight();
        // 查询es的结果
        List<Map<String, String>> resultMaps = modelHandler.getCountByModelType(startTimeTamp, endTimeTamp);
        log.info("服务类型数据量统计的结果为: {}", resultMaps);
        if (resultMaps != null && resultMaps.size() != 0) {
            // 更新访问趋势
            List<Map<String, String>> newTrend = resultMaps.stream().map(map -> {
                String type = map.getOrDefault("type", "");
                String count = map.getOrDefault("count", "0");
                Map<String, String> newMap = new HashMap<>();
                newMap.put("type", type);
                newMap.put("count_trend", updateTrend(type,count));
                return newMap;
            }).collect(Collectors.toList());
            log.info("服务类型数据量更新后的趋势: {}", newTrend);
            // 入库
            try {
                Boolean flag = trendManageService.insertTypeTrendCount(newTrend);
                log.info("服务类型数据量统计" + (flag ? "成功!":"失败!"));
            } catch (Exception e) {
                log.info("服务类型数据量统计失败: {}", e.getMessage());
            }
        }
    }

    public String updateTrend(String type, String count) {
        String trendCount = trendManageService.getTrendCount(null, type, FLAG);
        if (trendCount == null) {
            return "0,0,0,0,0,0," + count;
        }
        return trendCount.substring(2) + "," + count;
    }
}
