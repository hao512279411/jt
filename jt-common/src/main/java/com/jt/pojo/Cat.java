package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;

@TableName("tb_item_cat")
@Accessors(chain =true)
@Data
public class Cat extends BasePojo{

//    id                   bigint not null auto_increment,
    @TableId(type = IdType.AUTO)
    private Long id;
//    parent_id            bigint comment '父ID=0时，代表一级类目',
    private Long parentId;
//    name                 varchar(150),
    private String name;
//    status               int(1) default 1 comment '默认值为1，可选值：1正常，2删除',
    private Byte status=1;
//    sort_order           int(4) not null, 排序号
    private Short sortOrder;
//    is_parent            tinyint(1), 是否为父
    private Byte isParent;
////    created              datetime,   创建时间
//    private Date created;
////    updated              datetime,   修改时间
//    private Date updated;
}
