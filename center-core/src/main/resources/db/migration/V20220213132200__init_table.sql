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
    phone VARCHAR(12) not null COMMENT "电话号码",
    password VARCHAR(20) not null COMMENT "用户密码",
    role_pid int not null default 0 COMMENT "用户角色",
    role_cid int not null default 0 COMMENT "用户角色",
    is_delete TINYINT not null default 0,
    is_lock TINYINT not null default 0
    );
insert into t_user (username, phone,password, role_pid) VALUES ('admin', '66666666666','admin',0);
insert into t_user (username, phone,password, role_pid) VALUES ('zhejiang', '11111111111','zhejiang', 1);
insert into t_user (username, phone,password, role_pid, role_cid) VALUES ('hangzhou', '22222222222','hangzhou', 1,1);
insert into t_user (username, phone,password, role_pid) VALUES ('hubei','33333333333', 'hubei', 2);
insert into t_user (username, phone,password, role_pid, role_cid) VALUES ('wuhan', '44444444444','wuhan', 2,1);

