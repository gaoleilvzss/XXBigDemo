package com.vinsuan.xiangxuelearnbigdemo.rxjava;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/4
 * desc : 观察者
 */
public interface Observer {
    //被观察者 改变之后 可以接收到
    void update(Object o);
}
