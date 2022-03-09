CREATE TABLE `t_quartz_job`  (
    `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
    `task_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务id',
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务名称',
    `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'job引用地址',
    `cron` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'cron表达式',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
    `status` tinyint NOT NULL COMMENT '定时任务状态 0 停用,1启用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `t_quartz_job` (`task_id`, `name`,  `class_name`, `cron`,`description`, `status`) VALUES ('TB00001', '定时统计服务类型数据', 'com.itchenyang.task.ModelTypeTask', '0 * * * * ?', '每天凌晨统计服务类型数量', '1');
