package org.data.datacollector.services;

import java.lang.reflect.Field;

public class ClassHelper {

    private final Class<?> clazz;

    private final Object object;

    public ClassHelper(Class<?> clazz , Object object) {
        this.clazz = clazz;
        this.object = object;
    }

    public String getValueFieldByName(String fieldName){
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return "";
        }
    }
}
