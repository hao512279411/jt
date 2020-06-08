package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_content")
@Data
@Accessors
public class Content extends BasePojo{

//    id                   bigint not null auto_increment,
    @TableId(type = IdType.AUTO)
    private Long id;
//    category_id    内容分类ID      bigint,

    private Long categoryId;
//    title          内容标题      varchar(200),
    private String title;

//    sub_title       子标题     varchar(100) comment '例如：价格显示',
    private String subTitle;

//    title_desc      标题描述     varchar(500),
    private String titleDesc;

//    url           链接       varchar(300),
    private String url;

//    pic           图片绝对路径       varchar(300),
    private String pic;

//    pic2          图片2       varchar(300),
    private String pic2;

//    content        内容      text comment '活动页',
    private String content;


}
