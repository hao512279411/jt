package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterNode;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration  //标志是个配置类  一般和bean对象连用
@PropertySource("classpath:properties/redis.properties")
public class RedisConfig {

    /**
     * 配置单个Redis
     *
     */
    //获取配置信息 redis.shard=192.168.126.129:6379,192.168.126.129:6380,192.168.126.129:6381
    @Value("${redis.shards}")
    private String shards;


    @Bean
    public JedisCluster shards(){
        Set<HostAndPort> jedisShardInfoList = new HashSet();
        //1、将每组数据拆分
        String[] splitShards = shards.split(",");
        //2、循环 将每组数据 存入list
        for (String shardString: splitShards) {
            jedisShardInfoList.add(new HostAndPort(shardString.substring(0,shardString.indexOf(":")),Integer.parseInt(shardString.substring(shardString.indexOf(":")+1))));
        }
        //3创建redis集群对象
        JedisCluster jedisCluster =new JedisCluster(jedisShardInfoList);

        return jedisCluster;
    }



//    /**
//     * 配置单个Redis
//     *
//     */
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private Integer port;
//
//
//    @Bean
//    public Jedis jdeis(){
//        return new Jedis(host,port);
//    }

}
