package org.data.datacollector.dataExtractors.global;

import org.data.datacollector.services.ClassHelper;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ButeeData {

    public abstract String getEmployeeNumber();

    public abstract List<Date> getValidityEnds();

    protected List<Date> getValidityEnds(Integer max , Class<? extends ButeeData> clazz){

        ClassHelper ch = new ClassHelper(clazz , this);

        List<String> validityEndsList = IntStream.rangeClosed(1, ++max)
                .mapToObj(i -> ch.getValueFieldByName("validityEnd" + i))
                .filter(val -> val != null && !val.isEmpty())
                .toList();

        return validityEndsList.stream().map(Date::new).collect(Collectors.toList());
    };
}
