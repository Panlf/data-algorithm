package com.plf.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author panlf
 * @date 2021/1/7
 */
public class LinkedLRUCache<K,V> extends LinkedHashMap<K,V> {
    /**
     * 缓存限制
     */
    private int capacity;

    public LinkedLRUCache(int capacity){
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }


}
