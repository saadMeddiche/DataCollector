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

    private String id;

    private String jeppesenCode;

    private String category;

    private Date validityStart;

    private String validityEnd;

    private String userId;

    private String employeeNumber;

    private Long trainingId;

    private Date createdDate;

    private Date lastModifiedDate;
}
