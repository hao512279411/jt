package com.jt.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EasyUITree {
    private Long id;
    private String text;
    private String state;  // open选项/closed目录
    public void setState(Byte a){
        this.state=  a==0 ? "open" : "closed";
//        if (a==0){
//            this.state="open";
//        }else {
//            this.state="closed";
//        }
    }

}
