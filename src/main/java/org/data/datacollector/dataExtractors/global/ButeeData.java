package org.data.datacollector.dataExtractors.global;

import org.data.datacollector.services.ClassHelper;

import java.util.List;
import java.util.stream.IntStream;

public abstract class ButeeData {

    public abstract String getEmployeeNumber();

    public abstract List<String> getValidityEnds();

    protected List<String> getValidityEnds(Integer max , Class<? extends ButeeData> clazz){

        ClassHelper ch = new ClassHelper(clazz , this);

        return IntStream.rangeClosed(1, ++max)
                .mapToObj(i -> ch.getValueFieldByName("validityEnd" + i))
                .filter(val -> val != null && !val.isEmpty())
                .toList();
    };
}
