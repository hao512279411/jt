package com.jt;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.Cat;
import com.jt.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper =new ObjectMapper();
//
        List<Cat> catList =new ArrayList();
        Cat cat =new Cat();
        cat.setId(5L).setName("哈哈").setParentId(90L);
        catList.add(cat);
        catList.add(cat);




//
//        String s = objectMapper.writeValueAsString(cat);
//
//        System.out.println(s);

//        Cat cat1 = objectMapper.readValue(s, Cat.class);
//        System.out.println(cat);


//

//
//        String s = objectMapper.writeValueAsString(catList);
//
//        List<Cat> list = objectMapper.readValue(s,List.class);
//        System.out.println(list);

        String s = JsonUtil.toJson(catList);

//        objectMapper.readValue(s,   );
//        System.out.println(s);
        List list = JsonUtil.toObject(s, List.class);
//        Cat cat22 = (Cat)list.get(0);
//        System.out.println(cat22);
//        System.out.println(cat1.get(1).getName());



    }
}
