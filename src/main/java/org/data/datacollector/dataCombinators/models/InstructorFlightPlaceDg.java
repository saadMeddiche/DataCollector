package org.data.datacollector.dataCombinators.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorFlightPlaceDg {
    private String id;
    private String date;
    private String instructorId;
    private String instructorNumber;
    private String sieId;
    private String sieNumber;
    private String sieName;
}
