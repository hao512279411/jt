package com.jt.test01;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Test {

//    @Autowired
//    private Jedis jedis2 ;

//    @org.junit.jupiter.api.Test
//    public void testJedis(){
//        jedis2.set("a","aaa");
//        System.out.println(jedis2.get("a"));
//
//    }

    String host = "192.168.126.129";
    Integer port = 6379;
    Jedis jedis =new Jedis(host,port);

    @org.junit.jupiter.api.Test
    public void testRedis() throws InterruptedException {

//
//        String set = jedis.set("abc", "啊啊啊");
//        System.out.println(set);
//
//        String abc = jedis.get("abc");
//        System.out.println(abc);
//        jedis.flushAll();

//
//        if (jedis.exists("aaa")){
//            System.out.println("啊啊啊");
//        }else {
//            System.out.println("存在");
//        }


        jedis.setex("aaa",5,"你好");
        Thread.sleep(2000);
        System.out.println(jedis.ttl("aaa"));

        SetParams setParams =new SetParams();

        jedis.setnx("key","valu");

    }

    @org.junit.jupiter.api.Test
    public void hashTest(){
        jedis.hset("hash","id","1");
        jedis.hset("hash","name","haha");
        System.out.println(jedis.hgetAll("hash"));
        System.out.println(jedis.hkeys("hash"));
        System.out.println(jedis.hvals("hash"));




    }

    /**
     * 分片 redis
     */
    @org.junit.jupiter.api.Test
    public void testShards(){
        List<JedisShardInfo> shards = new ArrayList();
        shards.add(new JedisShardInfo("192.168.126.129",6379));
        shards.add(new JedisShardInfo("192.168.126.129",6380));
        shards.add(new JedisShardInfo("192.168.126.129",6381));
        ShardedJedis jedis = new ShardedJedis(shards);

        jedis.set("aaa","测试");
        System.out.println(jedis.get("aaa"));


    }

    /**
     * 测试哨兵机制
     */
    @org.junit.jupiter.api.Test
    public void testSentry(){

        Set<String> set = new HashSet();
        set.add("192.168.126.129:26379");
        JedisSentinelPool jedisSentinelPool =new JedisSentinelPool("mymaster",set);
        Jedis jedis=jedisSentinelPool.getResource();
        Jedis jedis2=jedisSentinelPool.getResource();
        jedis.set("key","value");
        System.out.println(jedis2.get("key"));
    }

    @org.junit.jupiter.api.Test
    public void testRedisCluster(){
        Set<HostAndPort> set =new HashSet();
        set.add(new HostAndPort("192.168.126.129",7000));
        set.add(new HostAndPort("192.168.126.129",7001));
        set.add(new HostAndPort("192.168.126.129",7002));
        set.add(new HostAndPort("192.168.126.129",7003));
        set.add(new HostAndPort("192.168.126.129",7004));
        set.add(new HostAndPort("192.168.126.129",7005));
        JedisCluster jedisCluster =new JedisCluster(set);
        jedisCluster.set("集群","集群测试");
        System.out.println(jedisCluster.get("集群"));
    }
}
