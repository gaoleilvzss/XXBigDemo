package com.vinsuan.xiangxuelearnbigdemo.inject;

import android.app.Activity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/7/31
 * desc :
 */
public class InjectUtils {
    public static void injectView(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            boolean annotationPresent = declaredField.isAnnotationPresent(injectView.class);
            if (annotationPresent) {
                //判断属性是否被injectView注解标明
                injectView annotation = declaredField.getAnnotation(injectView.class);
                assert annotation != null;
                //获得注解中设置的id
                int id = annotation.value();
                View view = activity.findViewById(id);
                declaredField.setAccessible(true); //允许操作private属性
                try {
                    declaredField.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void injectTest(Activity activity) {
        Class<? extends Activity> clz = activity.getClass();
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if(declaredField.isAnnotationPresent(injectView.class)){
                injectView annotation = declaredField.getAnnotation(injectView.class);
                assert annotation != null;
                int value = annotation.value();
                View view = activity.findViewById(value);
                declaredField.setAccessible(true);
                try {
                    declaredField.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
