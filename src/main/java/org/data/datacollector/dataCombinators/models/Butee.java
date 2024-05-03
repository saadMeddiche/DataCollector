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
public class Butee {

    private Long userIdFromDB;

    private Long employeeNumber;

    private String jeppesenCode;

    private String category;

    private Date validityStart;

    private Date validityEnd;

    private Long trainingId;

    private Date createdDate;

    private Date lastModifiedDate;
}
