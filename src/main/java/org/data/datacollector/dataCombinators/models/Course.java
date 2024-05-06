package org.data.datacollector.dataCombinators.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String id;

    private String duration;

    private String place;

    private String examDate;

    private Date courseDate;

    private String activityType;

    private String startTime;

    private String endTime;

    private String timeOfDay;

    private String presenceMarked;

    private String cat2;

    private String cat3;

    private String instructorId;

    private String employeeNumberOfInstructor;

    private String examinatorId;

    private String trainingId;

}
