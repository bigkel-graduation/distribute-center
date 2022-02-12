-- 创建省级表
create table if not EXISTS t_province
(
    id int primary key auto_increment not null COMMENT '省份id',
    name VARCHAR(10) not null default '待定' COMMENT '省份名称'
);
insert into t_province (name) VALUES ('浙江');
insert into t_province (name) VALUES ('湖北');
insert into t_province (name) VALUES ('江苏');

-- 创建市级表
create table if not EXISTS t_city
(
    id int primary key auto_increment not null COMMENT 'id',
    cid int not null DEFAULT 0 COMMENT '城市id',
    pid int not null DEFAULT 0 COMMENT '所属省份',
    name VARCHAR(20) not null DEFAULT '待定' COMMENT '城市名称'
);
insert into t_city (pid,cid,name) VALUES (1,1, '杭州');
insert into t_city (pid,cid,name) VALUES (1,2, '宁波');
insert into t_city (pid,cid,name) VALUES (2,1, '武汉');
insert into t_city (pid,cid,name) VALUES (2,2, '襄阳');
insert into t_city (pid,cid,name) VALUES (3,1, '南京');
insert into t_city (pid,cid,name) VALUES (3,2, '苏州');

-- 创建用户表
CREATE table if not EXISTS t_user
(
    id int primary key auto_increment not null COMMENT "用户id",
    username VARCHAR(10) not null COMMENT "用户名",
    password VARCHAR(20) not null COMMENT "用户密码",
    role VARCHAR(10) not null COMMENT "用户角色，总部=0、浙江杭州=1-1",
    is_delete TINYINT not null default 0
);
insert into t_user (username, password, role) VALUES ('admin', 'admin','0');
insert into t_user (username, password, role) VALUES ('zhejiang', 'zhejiang', '1');
insert into t_user (username, password, role) VALUES ('hangzhou', 'hangzhou', '1-1');
insert into t_user (username, password, role) VALUES ('hubei', 'hubei', '2');
insert into t_user (username, password, role) VALUES ('wuhan', 'wuhan', '2-1');

