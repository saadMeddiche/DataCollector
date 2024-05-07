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

    private String oldButeeDate; // Fill -------

    private String isLongAbsence; //Only for ProficiencyCheck

    private String isLongAbsenceControl; //Only for ProficiencyCheck

    private String quizEmailSent; //Only for ground courses

    private String traineeId; // Fill -------

    private String courseId; // Fill -------

    private String quizHistoryId;

    private String evaluation;

    private String RenewalFormIR49Id;

    private String quizPassed;

    private String quizResent;

    private String isPresent;

    private String moringPresence;

    private String afternoonPresence;

    String quizId;

    // Only for english course (to calculate butees)
    private String level;
}
