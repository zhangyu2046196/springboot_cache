package com.youyuan.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangyu
 * @version 1.0
 * @description
 * @date 2019/6/26 14:08
 */
@Component
public class MapCache<K,V> {

    public Map<K,V> map=new ConcurrentHashMap<K,V>();

    /**
     * 保存
     * @param k
     * @param v
     */
    public void put(K k,V v){
        map.put(k,v);
    }

    /**
     * 查询
     * @param k
     * @return
     */
    public V get(K k){
        return map.get(k);
    }

    /**
     * 删除
     * @param k
     * @return
     */
    public String remove(K k){
        map.remove(k);
        return "success";
    }
}
