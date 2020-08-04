package com.vinsuan.xiangxuelearnbigdemo.rxjava;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vinsuan.xiangxuelearnbigdemo.R;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/4
 * desc :
 */
public class ObserverActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observer_layout);
    }

    public void test(View view){
        String msg = "12345678";
        Observable observable = new WeChatServiceObservable();
        Observer user1 = new UserPerson("1");
        Observer user2 = new UserPerson("2");
        Observer user3 = new UserPerson("3");
        Observer user4 = new UserPerson("4");

        observable.addObserver(user1);
        observable.addObserver(user2);
        observable.addObserver(user3);
        observable.addObserver(user4);

        observable.putMessage(msg);
    }
}
