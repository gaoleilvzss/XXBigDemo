package com.vinsuan.xiangxuelearnbigdemo.rxjava;

import android.util.Log;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/4
 * desc :
 */
public class UserPerson implements Observer{
    private String name;
    private String message;

    public UserPerson(String name) {
        this.name = name;
    }

    @Override
    public void update(Object o) {
        message = (String) o;
        //读取消息
        readMessage(message);

    }

    private void readMessage(String message) {
        Log.d("gaolei",name+"收到了消息"+message);
    }
}
