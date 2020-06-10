package com.jt.ordersql;

import com.jt.pojo.OrderItem;
import org.springframework.data.repository.query.Param;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class OrderItemSQL {

   public String  insertItems(Map map){
       List<OrderItem> items = (List<OrderItem>) map.get("list");
       StringBuffer sql = new StringBuffer("insert into tb_order_item(item_id,order_id,num,title,price,total_fee,pic_path) values");
       MessageFormat mf = new MessageFormat("(#'{'list[{0}].itemId},#'{'list[{0}].orderId},#'{'list[{0}].num},#'{'list[{0}].title},#'{'list[{0}].price},#'{'list[{0}].totalFee},#'{'list[{0}].picPath})");
       for (int i = 0; i < items.size(); i++) {
           sql.append(mf.format(new Object[]{i}));
           if (i < items.size() - 1) {
               sql.append(",");
           }
       }
       return sql.toString();
   }
}
