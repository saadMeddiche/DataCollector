package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLineCheckResult {

    // Fields with values
    private String id;
    private String oldButeeDate;
    private String traineeId;
    private String lineCheckId;
    private String evaluationId;

    // Fields without values
    private String isLongAbsence;
    private String isLongAbsenceControl;
    private String isPresent;
    private String moringPresence;
    private String afternoonPresence;
}
