package com.jt.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

//商品描述表
@Data
@Accessors(chain = true)
@TableName("tb_item_desc")
public class ItemDesc extends BasePojo{


//    item_id    商品ID          bigint(10) not null comment '商品ID',
    @TableId
    private Long itemId;
//    item_desc   商品描述         text,
    private String ItemDesc;

}
