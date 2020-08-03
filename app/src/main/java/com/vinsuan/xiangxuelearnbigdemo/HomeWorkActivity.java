package com.vinsuan.xiangxuelearnbigdemo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vinsuan.xiangxuelearnbigdemo.annotation.AutoWired;
import com.vinsuan.xiangxuelearnbigdemo.annotation.AutoWiredUtils;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/1
 * desc :
 */
public class HomeWorkActivity extends AppCompatActivity {
    @AutoWired()
    public String test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoWiredUtils.AutoWiredInject(this);
        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();
    }
}
