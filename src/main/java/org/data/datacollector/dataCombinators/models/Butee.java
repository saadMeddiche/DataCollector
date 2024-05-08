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

    private String validityEnd;

    private String userId;
    private String userNumber;
}
