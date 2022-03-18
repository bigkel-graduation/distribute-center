alter table `t_user` drop column `ip`;
alter table `t_user` add column `from_address` varchar(20) default '杭州' comment '上次登录所在的地址' after `password`;
alter table `t_user` add column `to_address` varchar(20) default '杭州' comment '现在登录所在的地址' after `from_address`;

-- 创建ip对应的地址(用于模拟用户登录所在地)
create table if not EXISTS `t_address`
(
    id int primary key auto_increment not null COMMENT 'id',
    ip VARCHAR(20) not null default '127.0.0.1' COMMENT 'ip地址',
    address VARCHAR(20) not null default '杭州' COMMENT 'ip所在地'
);
insert into t_address (`ip`,`address`) VALUES ('10.50.2.1','杭州');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.2','武汉');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.3','上海');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.4','北京');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.5','西安');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.6','南昌');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.7','郑州');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.8','厦门');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.9','三亚');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.10','澳门');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.11','台北');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.12','香港');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.13','深圳');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.14','大庆');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.15','哈尔滨');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.16','海门');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.17','库尔勒');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.18','克拉玛依');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.19','乌鲁木齐');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.20','西宁');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.21','成都');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.22','重庆');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.23','长沙');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.24','北海');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.25','常州');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.26','荆门');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.27','荆州');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.28','包头');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.29','太原');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.30','泰州');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.31','牡丹江');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.32','平度');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.33','贵阳');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.34','柳州');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.35','银川');
insert into t_address (`ip`,`address`) VALUES ('10.50.2.36','张家界');