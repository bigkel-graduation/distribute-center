alter table `t_user` add column `description` text comment '对用户的具体描述';
alter table `t_user` add column `ip` varchar(20) default '127.0.0.1' comment '上次登录所在的ip地址' after `password`