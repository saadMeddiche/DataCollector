package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorFlightPlaceDgResult {
    private String id;
    private String date;
    private String instructorId;
    private String sieId;
}
