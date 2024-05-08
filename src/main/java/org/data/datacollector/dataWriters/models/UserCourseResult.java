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
    private String traineeId;
    private String courseId;
    private String oldButeeDate;

    // Fields without values
    private String isLongAbsence;
    private String isLongAbsenceControl;
    private String quizEmailSent;
    private String quizHistoryId;
    private String evaluation;
    private String RenewalFormIR49Id;
    private String quizPassed;
    private String quizResent;
    private String isPresent;
    private String moringPresence;
    private String afternoonPresence;
    private String quizId;
    private String level;

}
