# 数据库 
#创建数据库
DROP DATABASE IF EXISTS xudeceyifa_db;
CREATE DATABASE xudeceyifa_db;

#使用数据库 
use xudeceyifa_db;

#创建管理员表 
CREATE TABLE admin_tb(
admin_id int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
name varchar(255) COMMENT '管理员姓名',
cell_phone varchar(255) COMMENT '手机号',
email varchar(255) COMMENT '邮箱',
password varchar(255) COMMENT '密码',
create_date datetime COMMENT '账号创建时间',
last_login_date datetime COMMENT '最新登录时间',
PRIMARY KEY (admin_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='管理员表';

#创建测试表 
CREATE TABLE test_tb(
test_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'testid',
title varchar(255) COMMENT '标题',
type varchar(255) COMMENT '类型',
level int(11) COMMENT '等级',
img varchar(255) COMMENT '图片url',
update_date datetime COMMENT '更新日期',
PRIMARY KEY (test_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='测试表';

#创建问题表 
CREATE TABLE problem_tb(
problem_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'problemid',
name varchar(255) COMMENT '名称',
type varchar(255) COMMENT '类型',
img varchar(255) COMMENT '图片url',
update_date datetime COMMENT '更新日期',
test_id int(11) COMMENT '测试id外键',
PRIMARY KEY (problem_id),
CONSTRAINT FK_TEST_PROBLEM FOREIGN KEY (test_id) REFERENCES test_tb (test_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_test_id (test_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='问题表';

#创建答案表 
CREATE TABLE answer_tb(
answer_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'answeid',
name varchar(255) COMMENT '名称',
type varchar(255) COMMENT '类型',
img varchar(255) COMMENT '图片url',
result longtext COMMENT '返回结果',
update_date datetime COMMENT '更新日期',
problem_id int(11) COMMENT '问题id外键',
PRIMARY KEY (answer_id),
CONSTRAINT FK_PROBLEM_ANSWER FOREIGN KEY (problem_id) REFERENCES problem_tb (problem_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_problem_id (problem_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='答案表';


#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO admin_tb (name,cell_phone,email,password,create_date,last_login_date) 
VALUES ("聂跃","15111336587","278076304@qq.com","11874bb6149dd45428da628c9766b252",now(),now());  

