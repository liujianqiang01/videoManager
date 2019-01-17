package com.video.manager.model;

import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-16
 * @Description:
 */
public class BasePage {

    public static void page(int page, int pageSize, int count, int size, ModelMap map) {
        map.put("count", count);
        map.put("maxPage", count / 10);
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("nowBegin", pageSize * (page - 1) + 1);
        map.put("nowEnd", pageSize * (page - 1) + size);
    }

    public static void page(PageInfo page, ModelMap map) {
        map.put("list", page.getList());
        map.put("count", page.getTotal());
        map.put("maxPage", page.getTotal() / 10);
        map.put("page", page.getPageNum());
        map.put("pageSize", page.getPageSize());
        map.put("nowBegin", page.getPageSize() * (page.getPageNum() - 1) + 1);
        map.put("nowEnd", page.getPageSize() * (page.getPageNum() - 1) + page.getList().size());
    }

    //查询条件转换
    public static <T> void beanToMap(T t, ModelMap map) {
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                String key = fields[i].getName();
                String name = key.substring(0, 1).toUpperCase() + key.substring(1); // 将属性的首字符大写，方便构造get，set方法
                Method get = t.getClass().getMethod("get" + name);
                Object value = get.invoke(t);
                if ("".equals(value)) {
                    Method set = t.getClass().getMethod("set" + name, String.class);
                    set.invoke(t, new Object[]{null});
                }
                if (value != null) {
                    map.put(key, value);
                }
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        TOrder order = new TOrder();
        order.setMerchantId("");
        order.setOpenId("341");
        ModelMap map = new ModelMap();
        beanToMap(order, map);
        System.out.println(order);
    }
}