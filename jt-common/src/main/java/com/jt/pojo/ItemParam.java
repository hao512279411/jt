package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_item_param")
@ToString
public class ItemParam extends BasePojo{

//    id                   bigint not null auto_increment,
    @TableId(type = IdType.AUTO)
    private Long id;
//    item_cat_id      商品分类ID    bigint(20),
    private Long itemCatId;
    @TableField(exist = false)
//  商品分配的 名称
    private String itemCatName;
//    param_data      参数数据     text comment '格式为json格式',
    private String paramData;


}
