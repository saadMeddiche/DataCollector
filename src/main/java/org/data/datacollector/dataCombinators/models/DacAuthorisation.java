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
public class DacAuthorisation {
    private String id;
    private String type;
    private String autorisationNumber;
    private String startDate;
    private String endDate;
    private String userId;
    private String userNumber;
}
