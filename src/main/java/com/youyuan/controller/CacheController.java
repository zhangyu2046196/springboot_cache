package com.youyuan.controller;

import com.youyuan.cache.MapCache;
import com.youyuan.entity.User;
import com.youyuan.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangyu
 * @version 1.0
 * @description
 * @date 2019/6/26 14:12
 */
@RestController
public class CacheController {

    private static Log logger= LogFactory.getLog(CacheController.class);

    @Autowired
    private MapCache<String,Object> mapCache;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 保存
     * @param key
     * @param value
     */
    @RequestMapping("/put/{key}/{value}")
    public void put(@PathVariable String key,@PathVariable  Object value){
        mapCache.put(key,value);
    }

    /**
     * 查询
     * @param key
     * @return
     */
    @RequestMapping("/get/{key}")
    public String get(@PathVariable String key){
        return (String) mapCache.get(key);
    }

    /**
     * 删除
     * @param key
     * @return
     */
    @RequestMapping("/remove")
    public String remove(String key){
        return mapCache.remove(key);
    }

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping("/getUserList")
    public List<User> getUserList(){
        return userRepository.findAll();
    }

    @RequestMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Integer id){
        logger.info("查询用户信息:id"+id);
        return userRepository.getUserById(id);
    }

    @RequestMapping("/cacheClear")
    public void cacheClear(){
        cacheManager.getCache("userCache").clear();
    }

    @RequestMapping("/update")
    public void update(Integer id,String lastName){
        userRepository.update(id,lastName);
    }

}
