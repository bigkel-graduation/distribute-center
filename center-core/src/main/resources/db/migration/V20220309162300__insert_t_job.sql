INSERT INTO
    `t_quartz_job` (`task_id`, `name`,  `class_name`, `cron`,`description`, `status`)
VALUES
    ('TB00002', '定时统计服务名称数据', 'com.itchenyang.task.ModelNameTask', '0 0 0 * * ?', '每天凌晨统计不同类型的服务名称数量', '1');

ALTER TABLE `t_model_type` ADD unique key(`type`);
ALTER TABLE `t_model_name` ADD unique key(`type`,`name`);

update `t_quartz_job` set `cron` = '0 0 0 * * ?' where `task_id` = 'TB00002';