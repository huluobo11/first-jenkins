package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by wwj on 2018/12/01.
 */
@Service
public class VipRedisServiceImpl implements IVipRedisService {
    //声明
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> stringRedisTemplate;

    @Resource(name = "valOpsStr")
    ValueOperations<String, String> valOpsStr;

    @Resource(name = "redisTemplate")
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "valOpsObj")
    ValueOperations<Object, Object> valOpsObj;

    /**
     * 根据指定key获取String
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        return valOpsStr.get(key);
    }

    /**
     * 设置Str缓存
     *
     * @param key
     * @param val
     */
    public void setStr(String key, String val) {
        valOpsStr.set(key, val);
    }
    /**
     * 设置Str缓存
     *
     * @param key
     * @param val
     */
    public void setStr(String key , String val , long time ,TimeUnit seconds) {
        valOpsStr.set(key , val , time , seconds);
    }


    /**
     * 删除指定key
     *
     * @param key
     */
   public void del(String key) {
	   stringRedisTemplate.delete(key);
    }
    /**
     * 根据指定o获取Object
     *
     * @param o
     * @return
     */
    public Object getObj(Object o) {
        return valOpsObj.get(o);
    }

    /**
     * 设置obj缓存
     *
     * @param o1
     * @param o2
     */

    public void setObj(Object o1, Object o2) {
        valOpsObj.set(o1, o2);
    }

    /**
     * 设置obj 指定时间缓存
     * @param key
     * @param val
     * @param time
     * @param seconds
     */
    public void setObj(String key , Object val ,  long time ,TimeUnit seconds) {

        valOpsObj.set(key , val , time , seconds);
    }

    /**
     * 删除Obj缓存
     *
     * @param o
     */

    public void delObj(Object o) {
        redisTemplate.delete(o);
    }



}
