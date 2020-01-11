create database slxsm_mybatis_test;
drop table if not exists t_user;
create table t_user (
    id bigint auto_increment primary key comment '主键，用户id，自动增长',
    name varchar(32) not null default '' comment '姓名',
    age smallint not null default 1 comment '年龄',
    salary decimal(12,2) not null default 0 comment '薪水',
    sex tinyint not null default 0 comment '性别'
) comment '用户表';

DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键，订单id，自动增长',
    user_id BIGINT NOT NULL DEFAULT 0 COMMENT '用户ID',
    price DECIMAL(12,2) NOT NULL DEFAULT 0 COMMENT '订单金额'
)COMMENT '订单表';
INSERT INTO t_user(name,age,salary,sex) VALUES('slxsmStudy',30,6000,1),('zwj',30,10000,1),('ds_slxsm',40,30000,1);
INSERT INTO t_order(user_id,price) VALUES(1,88.88),(2,66.66);
