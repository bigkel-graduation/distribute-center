create table `t_request` (
    `id` int primary key NOT NULL AUTO_INCREMENT,
    `username` varchar(64) NOT NULL COMMENT '请求者名称',
    `phone` varchar(64) NOT NULL DEFAULT '11111111111' COMMENT '请求者电话',
    `role_pid` int NOT NULL DEFAULT 0,
    `role_cid` int NOT NULL DEFAULT 0,
    `create_time` timestamp NOT NULL DEFAULT NOW(),
    `action` tinyint NOT NULL DEFAULT 0 COMMENT '是否同意，1是同意 0是不同意',
    `stats` tinyint NOT NULL DEFAULT 0 COMMENT '是否处置此条消息，1是已处置 0是未处置'
);