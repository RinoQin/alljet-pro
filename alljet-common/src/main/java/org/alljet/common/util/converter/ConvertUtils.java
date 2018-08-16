package org.alljet.common.util.converter;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public class ConvertUtils {

    /**
     * 私有化构造函数
     */
    private ConvertUtils() {

    }

    /**
     * 转换list<bean>
     *
     * @param srcList srcList
     * @param clazz clazz
     * @param <T> 泛型
     * @return
     */
    public static <T> List<T> toList(List srcList, Class<T> clazz) {
        List<T> dtoList = new ArrayList<>();
        if (srcList != null && srcList.size() > 0) {
            for (Object item : srcList) {
                dtoList.add(toBean(item, clazz));
            }
        }
        return dtoList;
    }

    /**
     * 转换bean
     * @param srcObject srcObject
     * @param clazz clazz
     * @param <T> T
     * @return
     */
    public static <T> T toBean(Object srcObject, Class<T> clazz) {
        T dest = null;
        try {
            if (srcObject != null) {
                dest = clazz.newInstance();
                BeanUtils.copyProperties(srcObject, dest);
            }
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }


}
