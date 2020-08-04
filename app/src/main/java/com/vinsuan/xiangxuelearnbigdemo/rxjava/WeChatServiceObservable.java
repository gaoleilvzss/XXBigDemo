package com.vinsuan.xiangxuelearnbigdemo.rxjava;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/4
 * desc :
 */
public class WeChatServiceObservable implements Observable {
    //存储多个观察者容器
    private List<Observer> observers = new ArrayList<>();
    private String message;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public void putMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}
