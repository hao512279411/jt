package com.jt.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.Item;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/web")
@RestController
public class WebJSONPController {

    /**
     * URL: http://manage.jt.com/web/testJSONP?callback=hello&_=1591342032226
     */
    @RequestMapping("/testJSONP")
    public JSONPObject testJSONP(String callback){
        Item item =new Item();
        item.setId(1L).setImage("测试成功");
        return new JSONPObject(callback,item);
    }
}
