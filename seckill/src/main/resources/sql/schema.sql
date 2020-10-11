-- ���ݿ��ʼ���ű�

-- �������ݿ�
CREATE DATABASE seckill;

-- ʹ�����ݿ�
use seckill;

-- ������ɱ����
create table seckill(
seckill_id bigint not null auto_increment comment '��Ʒ���ID',
name varchar (120) not null comment '��Ʒ����',
number int not null comment '�������',
create_time timestamp not null default current_timestamp comment '����ʱ��',
start_time timestamp not null comment '��ɱ����ʱ��',
end_time timestamp not null comment '��ɱ����ʱ��',
primary key(seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine = InnoDB auto_increment=1000 default charset=utf8 comment='��ɱ����';


-- ��ʼ������
insert into seckill(name,number,start_time,end_time)
values
   ('1000��ɱiphone11Pro',100,'2020-4-17 00:00:00','2020-4-18 00:00:00'),
   ('100��ɱipad',200,'2020-4-17 00:00:00','2020-4-18 00:00:00'),
   ('300��ɱС��4',500,'2020-4-17 00:00:00','2020-4-18 00:00:00'),
   ('500��ɱ����note',300,'2020-4-17 00:00:00','2020-4-18 00:00:00');
   
-- ��ɱ�ɹ���ϸ��
-- �û���¼��֤��ص���Ϣ
create table success_killed(
`seckill_id` bigint NOT NULL COMMENT '��ɱ��Ʒid',
`user_phone` bigint NOT NULL COMMENT '�û��ֻ���',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '״̬��ʶ��-1����Ч 0���ɹ� 1���Ѹ��� 2���ѷ���',
`create_time` timestamp NOT NULL COMMENT '����ʱ��',
PRIMARY KEY (seckill_id,user_phone),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT =1000 DEFAULT CHARSET=UTF8 COMMENT='��ɱ�ɹ���ϸ��'

--�������ݿ����̨
net start mysql
mysql -u root -p 123





