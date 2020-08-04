package com.vinsuan.xiangxuelearnbigdemo.rxjava;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/4
 * desc : 被观察者
 */
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    void putMessage(String message);
}
