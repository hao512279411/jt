package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.ItemParam;

import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemParamMapper  extends BaseMapper<ItemParam> {

    @Select("select p.id,p.item_cat_id,p.created,p.param_data,p.updated ,c.name itemCatName from " +
            "tb_item_param p LEFT JOIN tb_item_cat c ON p.item_cat_id = c.id ORDER BY created DESC LIMIT 0,30")
    public List<ItemParam> selectItemParamAndCatName(@Param("page") Long page,@Param("rows") Integer rows);

}
