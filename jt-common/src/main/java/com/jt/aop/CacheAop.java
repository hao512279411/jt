package com.jt.aop;

import com.jt.anno.CacheFind;
import com.jt.anno.CacheUpdate;
import com.jt.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
public class CacheAop {
//    @Resource()
//    private Jedis jedis;

    @Resource
    private JedisCluster jedis;


    //新增缓存
    @Around("@annotation(cacheFind)")
    public Object around(ProceedingJoinPoint joinPoint, CacheFind cacheFind){

        Object results=null;
        //0.获取方法的所有参数 并且遍历相加
        StringBuffer paramName=new StringBuffer("::");
        Object[] args = joinPoint.getArgs();
        for (Object obj: args ) {
            paramName.append(obj.toString());
        }
        // 1. 拼接 keyName
        String keyName = cacheFind.parKey()+paramName;
        System.out.println("获取到的key============"+keyName);
        // 2.从缓存里获Json取值
        String  jsonResults = jedis.get(keyName);
        if (StringUtils.isEmpty(jsonResults)){ //如果没有值就
            try {
                System.out.println("数据库查询数据");
                results = joinPoint.proceed();
                //是否设置超时时间
                if (cacheFind.seconds()>0) {
                    jedis.setex(keyName, cacheFind.seconds(), JsonUtil.toJson(results));
                }else {
                    jedis.set(keyName, JsonUtil.toJson(results));
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }else { //redis获取到了值
            //将获取到的json 转换为对象
            System.out.println("缓存查询数据");
                results = JsonUtil.toObject(jsonResults,this.getReturnClazz(joinPoint));
        }
        return results;
    }

    //更新缓存
    @Around("@annotation(cacheUpdate)")
    public Object update(ProceedingJoinPoint joinPoint, CacheUpdate cacheUpdate){
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    //方法1  获取returnClazz
    private Class getReturnClazz(ProceedingJoinPoint joinPoint){
        //获取方法的返回值类型
        //1 先获取类对象
        Class clazz = joinPoint.getSignature().getDeclaringType();
        //1.1获取方法名字
        String methodName = joinPoint.getSignature().getName();

        //2 获取里面所有方法     并获取返回值类型
        Method[] methods = clazz.getMethods();
        for (Method methid:methods ) {
            if (methid.getName().equals(methodName)){//判断方法名是否相同
                return  methid.getReturnType();
            }
        }
        return null;
    }

}
