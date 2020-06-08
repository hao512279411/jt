package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

//商品规格 和商品的关系
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_item_param_item")
@ToString
public class ItemParamItem extends BasePojo{


    //    id                   bigint not null auto_increment,
    @TableId(type = IdType.AUTO)
    private Long id;
    //    item_id      商品ID    bigint(20),
    private Long itemId;
    //    param_data      参数数据     text comment '格式为json格式',
    private String paramData;

}
