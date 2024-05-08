package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResult {

    // Fields with values
    private String id;
    private String courseDate;
    private String activityType;
    private String instructorId;
    private String instructorNumber;
    private String place;
    private String presenceMarked;
    private String cat2;
    private String cat3;

    // Fields without values
    private String duration;
    private String examDate;
    private String startTime;
    private String endTime;
    private String timeOfDay;
    private String examinatorId;
    private String trainingId;
}
