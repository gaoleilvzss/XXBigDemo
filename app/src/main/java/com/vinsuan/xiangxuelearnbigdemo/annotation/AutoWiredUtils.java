package com.vinsuan.xiangxuelearnbigdemo.annotation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/1
 * desc :
 */
public class AutoWiredUtils {
    public static void AutoWiredInject(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Intent intent = activity.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(AutoWired.class)) {
                AutoWired annotation = declaredField.getAnnotation(AutoWired.class);
                assert annotation != null;
                String value = TextUtils.isEmpty(annotation.value()) ? declaredField.getName() : annotation.value();
                if (bundle.containsKey(value)) {
                    Object obj = bundle.get(value);
                    Class<?> componentType = declaredField.getType().getComponentType();
                    assert componentType != null;
                    if (declaredField.getType().isArray() &&
                            Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objects = (Object[]) obj;
                        assert objects != null;
                        obj = Arrays.copyOf(objects, objects.length, (Class<? extends Object[]>) declaredField.getType());
                    }

                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void Auto(Activity activity) {
        Class<? extends Activity> clz = activity.getClass();
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }

        Field[] declaredFields = clz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(AutoWired.class)) {
                AutoWired annotation = declaredField.getAnnotation(AutoWired.class);
                assert annotation != null;
                String value = TextUtils.isEmpty(annotation.value()) ? declaredField.getName() : annotation.value();
                if(extras.containsKey(value)){

                }



            }
        }

    }
}
