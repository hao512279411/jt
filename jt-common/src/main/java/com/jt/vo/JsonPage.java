package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors
public class JsonPage<T> {

    //总数量
    private Integer  total;
    //找到的对象
    private List<T> rows;


}
