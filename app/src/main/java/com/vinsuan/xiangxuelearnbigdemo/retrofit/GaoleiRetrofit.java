package com.vinsuan.xiangxuelearnbigdemo.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.vinsuan.xiangxuelearnbigdemo.R;
import com.vinsuan.xiangxuelearnbigdemo.annotation.OnClick;
import com.vinsuan.xiangxuelearnbigdemo.annotation.OnClickUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/2
 * desc :
 */
public class GaoleiRetrofit extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        OnClickUtils.onClick(this);
    }

    @OnClick({R.id.test1, R.id.test2})
    public void ProxyTest(View view){
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }


}
