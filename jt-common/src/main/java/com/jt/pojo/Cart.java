package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 购物车对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_cart")
public class Cart extends BasePojo{

//    id    |               bigint(20) not null auto_increment,
    @TableId(type = IdType.AUTO)
    private Long id;
//    user_id              bigint(20),
    private Long userId;
//    item_id              bigint(20),
    private Long itemId;
//    item_title           varchar(100),
    private String itemTitle;
//    item_image           varchar(200),
    private String itemImage;
//    item_price           bigint(20) comment '单位：分',
    private Long itemPrice;
//    num                  int(10),
    private Integer num;

}
