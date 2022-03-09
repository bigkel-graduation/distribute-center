create table `t_model_type` (
    `id` int primary key NOT NULL AUTO_INCREMENT,
    `type` varchar(64) NOT NULL COMMENT '统计的具体服务类型名称',
    `count_trend` varchar(64) NOT NULL DEFAULT '0,0,0,0,0,0,0' COMMENT '统计服务类型一星期的趋势'
);

create table `t_model_name` (
    `id` int primary key NOT NULL AUTO_INCREMENT,
    `name` varchar(64) NOT NULL COMMENT '统计的具体服务名称',
    `type` varchar(64) NOT NULL COMMENT '服务所属的具体服务类型',
    `count_trend` varchar(64) NOT NULL DEFAULT '0,0,0,0,0,0,0' COMMENT '统计服务一星期的趋势'
)