package top.cuizilin.dby.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectUtil {
    /**
     * 将src对象的属性合并到target里面
     * @param src
     * @param target
     */
    public static void mergeObj(Object src, Object target) throws Exception{
        Class<?> targetC = target.getClass();
        Class<?> sourceC = src.getClass();
        List<String> targetFields = new ArrayList<>();
        List<String> srcFields = new ArrayList<>();
        Map<String, Class<?>> targetFieldMap = new HashMap<>();

        for(Field tf : targetC.getDeclaredFields()){
            targetFields.add(tf.getName());
            targetFieldMap.put(tf.getName(), tf.getType());
        }

        for(Field sf : sourceC.getDeclaredFields()){
            srcFields.add(sf.getName());
        }
        targetFields.retainAll(srcFields);


        for(String field : targetFields){
            String setMethodName = "set" + StringUtils.capitalize(field);
            Method setMethod = targetC.getMethod(setMethodName, targetFieldMap.get(field));
            String getMethodName = "get" + StringUtils.capitalize(field);
            Method getMethod = sourceC.getMethod(getMethodName);
            setMethod.invoke(target, getMethod.invoke(src));
        }
    }
}
