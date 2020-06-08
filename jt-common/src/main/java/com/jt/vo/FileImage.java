package com.jt.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//封装的 给 前端返回 文件是否 上传成功的 json对象
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileImage {

    //成功 0  失败1
    private Byte error;
    //网络路径
    private String url;
    //宽高
    private Integer width;
    private Integer height;


    /**
     *  图片保存成功
     * @param url
     * @param width     宽
     * @param height    高
     * @return          vo对象
     */
    public static FileImage succeed(String url,Integer width,Integer height){
       return new FileImage((byte)0,url,width,height);
    }

    public static FileImage fail(){
        return new FileImage((byte)1,null,null,null);
    }


}
