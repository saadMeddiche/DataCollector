package org.data.datacollector.dataExtractors.globalDataHolders;

import lombok.Builder;
import lombok.Getter;
import org.data.datacollector.services.ClassHelper;

import java.util.List;
import java.util.stream.IntStream;

public abstract class UserCourseData {
    public abstract String getEmployeeNumber();

    public abstract List<Row> getRows();

    protected List<Row> getRows(Integer max , Class<? extends UserCourseData> clazz){

        ClassHelper ch = new ClassHelper(clazz , this);

        return IntStream.range(1, ++max)
                .mapToObj(i -> Row.builder()
                        .courseDate(ch.getValueFieldByName("courseDate" + i))
                        .instructorNumber(ch.getValueFieldByName("instructorNumber" + i))
                        .validityEnd(ch.getValueFieldByName("validityEnd" + i))
                        .level(ch.getValueFieldByName("level" + i))
                        .build())
                .toList();
    }

    @Builder
    @Getter
    public static class Row {

        private String courseDate;

        private String instructorNumber;

        private String validityEnd;

        private String level;

    }
}
