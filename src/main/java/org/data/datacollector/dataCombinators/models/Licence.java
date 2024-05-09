package org.data.datacollector.dataCombinators.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Licence {

    private String id;

    private String validTo;

    private String examinerEmployeeNumber;
    private String examinerName;

    private String userId;
    private String userNumber;
}
