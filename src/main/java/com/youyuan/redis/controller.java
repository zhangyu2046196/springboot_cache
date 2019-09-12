package com.youyuan.redis;

import com.youyuan.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyu
 * @version 1.0
 * @description
 * @date 2019/6/28 16:07
 */
@RestController
public class controller {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/set")
    public String setString(String key,String obj){
        redisService.set(key,obj,60L);
        return "success";
    }

    @RequestMapping("/get")
    public String get(String key){
        return redisService.getString(key);
    }

    @RequestMapping("/incr")
    public long incr(String key){
        return redisService.incr(key);
    }

}
