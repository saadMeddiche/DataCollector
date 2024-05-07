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
public class Cat {

    private String id;

    //CAT II ou CAT III
    private String type;

    //FINAL ou Transition
    private String category;

    private String inst;

    private String date;

    private String pntId;

    private String employeeNumberOfPnt;

    private String instructorId;

    private String employeeNumberOfInstructor;
}
