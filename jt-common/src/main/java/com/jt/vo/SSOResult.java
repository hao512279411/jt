package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SSOResult {

    private Integer status;      //200 成功，201 没有查到
    private String msg;         //“OK”  //返回信息消息
    private Boolean data;       //false  //返回数据true用户已存在，false用户不存在，可以



}
