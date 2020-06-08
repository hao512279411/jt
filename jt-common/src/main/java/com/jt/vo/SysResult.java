package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
@NoArgsConstructor
@AllArgsConstructor
public class SysResult<T> implements Serializable {
    private static final long serialVersionUID = -1933432746036138417L;
    private Integer status;  //状态值 200为成功
    private String msg;      //失败提示 字符串
    private T data;      //返回的对象

    public SysResult(Integer status){
        this.status=status;
    }

    //编辑公共的API ,简化用户的调用
    //成功,无数据
    public static SysResult success(){
       return new SysResult(200,"操作成功",null);
    }

    //成功,有数据
    public static SysResult success(Object data){
        return new SysResult(200,"操作成功",data);
    }

    //成功,有数据  有字符串
    public static SysResult success(String msg,Object data){
        return new SysResult(200,msg,data);
    }

    //失败,没有数据
    public static SysResult fail(){
        return new SysResult(201,"操作失败",null);
    }
    //失败,有数据
    public static SysResult fail(Object data){
        return new SysResult(201,"操作失败",data);
    }

    //失败,有数据  有字符串
    public static SysResult fail(String msg,Object data){
        return new SysResult(201,msg,data);
    }
}
