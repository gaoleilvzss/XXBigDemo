package com.vinsuan.xiangxuelearnbigdemo.annotation;

import android.view.View;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/2
 * desc :
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerType = View.OnClickListener.class,listenerSetter = "setListener")
public @interface OnClick {
    @IdRes int[] value();
}
