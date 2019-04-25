
create DATABASE distribution;

-- 1.用户表
create TABLE distribution.`t_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `user_level` varchar(50) DEFAULT NULL COMMENT '用户等级 0消费者，1会员，2，初级合伙人 3，高级合伙人，4,分公司合伙人',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级id',
  `extract_money` decimal(30,18) DEFAULT NULL COMMENT '累计提现',
  `surplus_money` decimal(30,18) DEFAULT NULL COMMENT '待提现',
  `income_money` decimal(30,18) DEFAULT NULL COMMENT '累计收入',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
insert t_user VALUES ('1' ,'张三','18888888888','231093111111112332','0','9109999','222','0000001','100','10','90','2019-04-14','0');


-- 上下级对应关系
create TABLE distribution.`t_user_relation` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `child_id` varchar(32) DEFAULT NULL COMMENT '下级id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='当前用户上下级对应关系';

