package com.vinsuan.xiangxuelearnbigdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.TypeReference;
import com.vinsuan.lib_annotation.Gaolei;
import com.vinsuan.xiangxuelearnbigdemo.generic.GenericActivity;
import com.vinsuan.xiangxuelearnbigdemo.inject.InjectUtils;
import com.vinsuan.xiangxuelearnbigdemo.inject.injectView;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/7/31
 * desc :
 */
@Gaolei(a = "a")
public class MainActivity extends AppCompatActivity {
    @injectView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.injectView(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void gotoGeneric(View view) {
        startActivity(new Intent(this, GenericActivity.class));
    }

    public void gotoAutoWired(View view){
        Intent intent = new Intent(this, HomeWorkActivity.class);
        intent.putExtra("test","test");
        startActivity(intent);
    }
}
