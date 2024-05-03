package org.data.datacollector.dataExtractors.global;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ButeeData {

    public abstract Long getEmployeeNumber();

    public abstract List<Date> getValidityEnds();

    protected List<Date> getValidityEnds(Integer max , Class<? extends ButeeData> clazz){
        List<String> validityEndsList = IntStream.rangeClosed(1, max)
                .mapToObj(i -> {
                    try {
                        Field field = clazz.getDeclaredFields()[i];
                        field.setAccessible(true);
                        return (String) field.get(this);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(val -> val != null && !val.isEmpty())
                .toList();

        return validityEndsList.stream().map(Date::new).collect(Collectors.toList());
    };
}
