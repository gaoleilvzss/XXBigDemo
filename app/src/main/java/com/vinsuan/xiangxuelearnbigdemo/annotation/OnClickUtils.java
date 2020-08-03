package com.vinsuan.xiangxuelearnbigdemo.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/2
 * desc :
 */
public class OnClickUtils {
    public static void onClick(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(EventType.class)) {
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    Class listenerType = eventType.listenerType();
                    String listenerSetter = eventType.listenerSetter();
                    try {
                        Method value = annotationType.getDeclaredMethod("value");
                        int[] viewIds = (int[]) value.invoke(annotation);

                        declaredMethod.setAccessible(true);
                        ListenerInvocationHandler<Activity> listenerInvocationHandler
                                = new ListenerInvocationHandler(value,activity);
                        Object listenerProxy = Proxy.newProxyInstance(listenerType.getClassLoader()
                                , new Class[]{listenerType}, listenerInvocationHandler);

                        for (int viewId : viewIds) {
                            View view = activity.findViewById(viewId);
                            Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                            setter.invoke(view,listenerProxy);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ListenerInvocationHandler<T> implements InvocationHandler {

        private Method method;
        private T target;

        public ListenerInvocationHandler(Method method, T target) {
            this.method = method;
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target, args);
        }
    }
}
