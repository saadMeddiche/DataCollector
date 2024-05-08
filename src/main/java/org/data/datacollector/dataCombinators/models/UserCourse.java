package org.data.datacollector.dataCombinators.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourse {

    private String id;
    private String courseId;

    private String traineeId;
    private String traineeNumber;

    private String instructorId;
    private String instructorNumber;

    private String activityType;

    private String courseDate;
    private String oldButeeDate;


}
