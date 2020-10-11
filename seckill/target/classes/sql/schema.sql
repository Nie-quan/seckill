-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;

-- 使用数据库
use seckill;

-- 创建秒杀库存表
create table seckill(
seckill_id bigint not null auto_increment comment '商品库存ID',
name varchar (120) not null comment '商品名称',
number int not null comment '库存数量',
create_time timestamp not null default current_timestamp comment '创建时间',
start_time timestamp not null comment '秒杀开启时间',
end_time timestamp not null comment '秒杀结束时间',
primary key(seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine = InnoDB auto_increment=1000 default charset=utf8 comment='秒杀库存表';


-- 初始化数据
insert into seckill(name,number,start_time,end_time)
values
   ('1000秒杀iphone11Pro',100,'2020-4-17 00:00:00','2020-4-18 00:00:00'),
   ('100秒杀ipad',200,'2020-4-17 00:00:00','2020-4-18 00:00:00'),
   ('300秒杀小米4',500,'2020-4-17 00:00:00','2020-4-18 00:00:00'),
   ('500秒杀红米note',300,'2020-4-17 00:00:00','2020-4-18 00:00:00');
   
-- 秒杀成功明细表
-- 用户登录认证相关的信息
create table success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识：-1：无效 0：成功 1：已付款 2：已发货',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT =1000 DEFAULT CHARSET=UTF8 COMMENT='秒杀成功明细表'

--连接数据库控制台
net start mysql
mysql -u root -p 123





