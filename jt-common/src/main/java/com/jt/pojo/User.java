package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

//用户表
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
@Accessors(chain = true)
@Data
@EqualsAndHashCode
@ToString
public class User extends BasePojo implements Serializable {

    private static final long serialVersionUID = -45859811614223037L;
    //    id                   bigint not null auto_increment,
    @TableId(type = IdType.AUTO)
    private Long id;

//    username             varchar(50),
    private String username;

//    password             varchar(32) comment 'MD5加密',
    private String password;

//    phone    电话            varchar(20),
    private String phone;

//    email    邮箱            varchar(50),
    private String email;



}
