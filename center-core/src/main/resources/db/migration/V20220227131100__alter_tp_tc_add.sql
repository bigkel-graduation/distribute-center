alter table `t_province` add column `is_used` TINYINT not null default 0 comment '当前省角色是否被添加';
alter table `t_city` add column `is_used` TINYINT not null default 0 comment '当前市角色是否被添加';

-- 插入业务所在省份和城市
insert into t_province (name) VALUES ('广东'),('安徽'),('湖南'),('江西'),('河北'),('河南'),('福建'),('山东');
insert into t_city (pid,cid,name) VALUES (4,1, '广州'),(4,2,'东莞'),
                                         (5,1,'合肥'),(5,2,'宿迁'),
                                         (6,1, '长沙'),(6,2,'岳阳'),
                                         (7,1,'南昌'),(7,2,'九江'),
                                         (8,1, '石家庄'),(8,2,'衡水'),
                                         (9,1,'郑州'),(9,2,'洛阳'),
                                         (10,1, '福州'),(10,2,'厦门'),
                                         (11,1,'济南'),(11,2,'青岛');