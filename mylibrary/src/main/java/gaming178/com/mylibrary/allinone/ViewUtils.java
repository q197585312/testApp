package gaming178.com.mylibrary.allinone;

import android.app.Activity;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import gaming178.com.mylibrary.allinone.util.LogUtils;
import gaming178.com.mylibrary.allinone.view.EventListenerManager;
import gaming178.com.mylibrary.allinone.view.ResLoader;
import gaming178.com.mylibrary.allinone.view.ViewFinder;
import gaming178.com.mylibrary.allinone.view.ViewInjectInfo;
import gaming178.com.mylibrary.allinone.view.annotation.ContentView;
import gaming178.com.mylibrary.allinone.view.annotation.PreferenceInject;
import gaming178.com.mylibrary.allinone.view.annotation.ResInject;
import gaming178.com.mylibrary.allinone.view.annotation.ViewInject;
import gaming178.com.mylibrary.allinone.view.annotation.event.EventBase;


/**
 * IOC框架  控制反转  用反射注解方便的 替代findviewbyid，setContentView等常用方法，有效减少代码量。
 *
 * @author <a href="http://www.xunhou.me" target="_blank">Kelvin</a>
 */
public class ViewUtils {

    private ViewUtils() {
    }

    public static void inject(View view) {
        injectObject(view, new ViewFinder(view));
    }

    public static void inject(Activity activity) {
        injectObject(activity, new ViewFinder(activity));
    }

    public static void inject(PreferenceActivity preferenceActivity) {
        injectObject(preferenceActivity, new ViewFinder(preferenceActivity));
    }

    public static void inject(Object handler, View view) {
        injectObject(handler, new ViewFinder(view));
    }

    public static void inject(Object handler, Activity activity) {
        injectObject(handler, new ViewFinder(activity));
    }

    public static void inject(Object handler, PreferenceGroup preferenceGroup) {
        injectObject(handler, new ViewFinder(preferenceGroup));
    }

    public static void inject(Object handler,
                              PreferenceActivity preferenceActivity) {
        injectObject(handler, new ViewFinder(preferenceActivity));
    }

    @SuppressWarnings("ConstantConditions")
    private static void injectObject(Object handler, ViewFinder finder) {

        Class<?> handlerType = handler.getClass();

        // inject ContentView
        ContentView contentView = handlerType.getAnnotation(ContentView.class);
        if (contentView != null) {
            try {
                Method setContentViewMethod = handlerType.getMethod(
                        "setContentView", int.class);
                setContentViewMethod.invoke(handler, contentView.value());
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
            }
        }

        // inject view
        Field[] fields = handlerType.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                if (viewInject != null) {
                    try {
                        View view = finder.findViewById(viewInject.value(),
                                viewInject.parentId());
                        if (view != null) {
                            field.setAccessible(true);
                            field.set(handler, view);
                        }
                    } catch (Throwable e) {
                        LogUtils.e(e.getMessage(), e);
                    }
                } else {
                    ResInject resInject = field.getAnnotation(ResInject.class);
                    if (resInject != null) {
                        try {
                            Object res = ResLoader.loadRes(resInject.type(),
                                    finder.getContext(), resInject.id());
                            if (res != null) {
                                field.setAccessible(true);
                                field.set(handler, res);
                            }
                        } catch (Throwable e) {
                            LogUtils.e(e.getMessage(), e);
                        }
                    } else {
                        PreferenceInject preferenceInject = field
                                .getAnnotation(PreferenceInject.class);
                        if (preferenceInject != null) {

                            try {
                                Preference preference = finder
                                        .findPreference(preferenceInject
                                                .value());
                                if (preference != null) {
                                    field.setAccessible(true);
                                    field.set(handler, preference);
                                }
                            } catch (Throwable e) {
                                LogUtils.e(e.getMessage(), e);
                            }
                        }
                    }
                }
            }
        }

        // inject event
        Method[] methods = handlerType.getDeclaredMethods();
        if (methods != null && methods.length > 0) {
            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                if (annotations != null && annotations.length > 0) {
                    for (Annotation annotation : annotations) {
                        Class<?> annType = annotation.annotationType();
                        if (annType.getAnnotation(EventBase.class) != null) {
                            method.setAccessible(true);
                            try {
                                // ProGuard：-keep class * extends
                                // java.lang.annotation.Annotation { *; }
                                Method valueMethod = annType
                                        .getDeclaredMethod("value");
                                Method parentIdMethod = null;
                                try {
                                    parentIdMethod = annType
                                            .getDeclaredMethod("parentId");
                                } catch (Throwable e) {
                                }
                                Object values = valueMethod.invoke(annotation);
                                Object parentIds = parentIdMethod == null ? null
                                        : parentIdMethod.invoke(annotation);
                                int parentIdsLen = parentIds == null ? 0
                                        : Array.getLength(parentIds);
                                int len = Array.getLength(values);
                                for (int i = 0; i < len; i++) {
                                    ViewInjectInfo info = new ViewInjectInfo();
                                    info.value = Array.get(values, i);
                                    info.parentId = parentIdsLen > i ? (Integer) Array
                                            .get(parentIds, i) : 0;
                                    EventListenerManager.addEventMethod(finder,
                                            info, annotation, handler, method);
                                }
                            } catch (Throwable e) {
                                LogUtils.e(e.getMessage(), e);
                            }
                        }
                    }
                }
            }
        }
    }

}
