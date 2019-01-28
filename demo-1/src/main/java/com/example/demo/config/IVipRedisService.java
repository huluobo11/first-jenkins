package com.example.demo.config;

import java.util.concurrent.TimeUnit;

/**
 * Created by wwj on 2018/12/01.
 */
public interface IVipRedisService {

    String getStr(String key);

    void setStr(String key, String val);

    void setStr(String key, String val, long time, TimeUnit seconds);

    void del(String key);

    Object getObj(Object o);

    void setObj(Object o1, Object o2);

    void setObj(String key, Object val, long time, TimeUnit seconds);

    void delObj(Object o);

}
