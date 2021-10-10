CREATE DATABASE demo;  -- 创建数据库

USE demo;

-- 建表
CREATE TABLE demo.user_register(
 `id` bigint(11) NOT NULL AUTO_INCREMENT,  -- 生产用自增要当心溢出
 `user_id` varchar(32) NOT NULL COMMENT '用户id',
 `mobile_no` varchar(11)  NOT NULL COMMENT '手机号',
 `login_name`  varchar(20) NOT NULL COMMENT '登录名',
 `password` varchar(32) NOT NULL COMMENT '登录密码hash',
 `role` varchar(32) NOT NULL COMMENT '角色',
 `nick_name` varchar(32) COMMENT '昵称',
 `active_status` varchar(1) NOT NULL  COMMENT '用户状态，A:注册, D:注销, P:流程中',
 `remark` text COMMENT '备注',

 `update_time` datetime NOT NULL COMMENT '更新时间',
 `create_time` datetime NOT NULL COMMENT '创建时间',

  PRIMARY KEY `pk_id` (`id`),  -- 主键
  UNIQUE KEY `uq_mobile_no` (`mobile_no`), -- 唯一性
  UNIQUE KEY `uq_login_name` (`login_name`),
  UNIQUE KEY `uq_user_id` (`user_id`),
  KEY `ind_login_name` (`login_name`) USING BTREE, -- 建立索引，查询更快
  KEY `ind_user_id` (`user_id`) USING BTREE, -- 建立索引，查询更快
  KEY `ind_mobile_no` (`mobile_no`) USING BTREE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户注册表';
