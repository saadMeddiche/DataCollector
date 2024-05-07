package org.data.datacollector.dataExtractors.global;

import lombok.Builder;
import lombok.Getter;
import org.data.datacollector.services.ClassHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

public abstract class CourseData {

    public abstract String getEmployeeNumber();

    public abstract List<Row> getRows();

    protected List<Row> getRows(Integer max , Class<? extends CourseData> clazz){

        ClassHelper ch = new ClassHelper(clazz , this);

        return IntStream.range(1, ++max)
                .mapToObj(i -> Row.builder()
                        .courseDate(ch.getValueFieldByName("courseDate" + i))
                        .employeeNumberOfInstructor(ch.getValueFieldByName("instructorNumber" + i))
                        .catTwo(ch.getValueFieldByName("catTwo" + i))
                        .catThree(ch.getValueFieldByName("catThree" + i))
                        .place(ch.getValueFieldByName("place" + i))
                        .build())
                .toList();
    }

    @Builder
    @Getter
    public static class Row {

        private String courseDate;

        private String employeeNumberOfInstructor;

        private String catTwo;

        private String catThree;

        private String place;

    }


}
