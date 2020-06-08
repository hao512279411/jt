package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

//用户表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
@Accessors(chain = true)
public class User extends BasePojo{

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
