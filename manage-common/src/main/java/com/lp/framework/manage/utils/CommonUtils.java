package com.lp.framework.manage.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 常用工具类
 */
public class CommonUtils {

    /**
     * 移除空的key
     * @param map
     */
    public static void remmoveEmpty(Map<String,Object> map){
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            if(ObjectUtils.isEmpty(map.get(key))){
                iterator.remove();
            }
        }
    }

    public static Map<String, Object> getParametersMap(ServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    params.put(paramName, values);
                } else {
                    params.put(paramName, values[0]);
                }
            }
        }
        remmoveEmpty(params);
        return params;
    }
}
