package com.youyuan.redis.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyu
 * @version 1.0
 * @description redis业务类
 * @date 2019/6/28 15:49
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 保存
     * @param key
     * @param obj
     * @param time
     */
    public void set(String key,Object obj,Long time){
        if (StringUtils.isNotBlank(key)){
            if (obj instanceof String){
                setString(key,obj);
            }

            if (obj instanceof List){
                setList(key,obj);
            }

            if (time!=null){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * 保存字符串 数据
     * @param key
     * @param obj
     */
    public void setString(String key,Object obj){
        redisTemplate.opsForValue().set(key, (String) obj);
    }

    /**
     * 保存列表 数据
     * @param key
     * @param obj
     */
    public void setList(String key,Object obj){
        List<String> list= (List<String>) obj;
        for(String str:list){
            redisTemplate.opsForList().leftPush(key,str);
        }
    }

    /**
     * 查询 字符串 数据
     * @param key
     * @return
     */
    public String getString(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 自增
     * @param key  key
     * @return  返回自增的值
     */
    public long incr(String key){
        long val=redisTemplate.opsForValue().increment(key,1);
        if (val==1){
            redisTemplate.expire(key,3, TimeUnit.HOURS);
        }
        return val;
    }

}
