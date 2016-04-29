package com.ppandroid.whitefm.utils.anotationUtils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yeqinfu on 2016/4/7. 注解解释工具
 */
public class Utils_Anotation {

	public static void injectObject(Object object, Activity activity) {

		Class<?> classType = object.getClass();
        anotationFG(object, classType);
        anotationAC(object, classType);
       // anotationWidget(object, activity, classType);


    }

    private static void anotationWidget(Object object, Activity activity, Class<?> classType) {
        // 返回 Field 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的成员变量，
        // 包括公共、保护、默认（包）访问和私有成员变量，但不包括继承的成员变量。
        Field[] fields = classType.getDeclaredFields();

        if (null != fields && fields.length > 0) {

            for (Field field : fields) {
                // 该成员变量是否存在ContentWidget类型的注解
                if (field.isAnnotationPresent(IContentWidget.class)) {

                    IContentWidget annotation = field.getAnnotation(IContentWidget.class);
                    int viewId = annotation.value();
                    View view = activity.findViewById(viewId);
                    if (null != view) {
                        try {
                            field.setAccessible(true);
                            field.set(object, view);
                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }

            }

        }
    }

    private static void anotationAC(Object object, Class<?> classType) {
        // 该类是否存在IACContentView类型的注解
        if (classType.isAnnotationPresent(IACContentView.class)) {
            // 返回存在的ContentView类型的注解
            IACContentView annotation = classType.getAnnotation(IACContentView.class);

            try {

                // 返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
                Method method = classType.getMethod("setContentView", int.class);
                method.setAccessible(true);
                int resId = annotation.value();
                method.invoke(object, resId);

            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private static void anotationFG(Object object, Class<?> classType) {
        //该类是否存在IFGContentView类型的注解
        if (classType.isAnnotationPresent(IFGContentView.class)) {
            // 返回存在的ContentView类型的注解
            IFGContentView annotation = classType.getAnnotation(IFGContentView.class);
            try {

                // 返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
                Method method = classType.getMethod("onCreateView", LayoutInflater.class,ViewGroup.class,Bundle.class);
                method.setAccessible(true);
                int resId = annotation.value();
                method.invoke(object, resId);
                method.getReturnType();

            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}
