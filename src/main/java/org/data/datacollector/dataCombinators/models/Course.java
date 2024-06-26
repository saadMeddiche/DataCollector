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

    private String courseDate;

    private String activityType;

    private String instructorId;
    private String instructorNumber;

    private String place;

    private String presenceMarked;

    private String cat2;
    private String cat3;

}
