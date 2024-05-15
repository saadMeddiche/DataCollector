package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseResult {

    // Fields with values
    private String id;
    private String employeeId;
    private String courseId;
    private String oldButeeDate;

    // Fields without values
    private String isLongAbsence;
    private String quizHistoryId;
    private String evaluationId;
    private String renewalFormir49Id;
    private String quizPassed;
    private String isPresent;
    private String moringPresence;
    private String afternoonPresence;
    private String level;

}
