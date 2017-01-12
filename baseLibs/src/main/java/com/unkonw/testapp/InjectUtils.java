package com.unkonw.testapp;

import android.content.Context;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class InjectUtils {
    public static void inject(Context context){
        injectLayout(context);
        injectView(context);
    }
    private static void injectView(Context context) {
        Class<?> clazz=context.getClass();
        Class<?>[] declaredClasses = clazz.getDeclaredClasses();
        for (Class<?> declaredClass : declaredClasses) {
            ViewInject annotationsByType = declaredClass.getAnnotation(ViewInject.class);
        }
    }
    private static void injectLayout(Context context) {

    }


}
