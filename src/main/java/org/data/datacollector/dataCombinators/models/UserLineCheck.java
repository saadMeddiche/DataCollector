package org.data.datacollector.dataCombinators.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLineCheck {

    private String id;

    private String oldButeeDate;

    private String traineeId;
    private String traineeNumber;

    private String instructorNumber;
    private String dateOfOrigin;

    private String lineCheckId;

    private String isLongAbsence;
    private String isLongAbsenceControl;
    private String isPresent;
    private String moringPresence;
    private String afternoonPresence;
}
