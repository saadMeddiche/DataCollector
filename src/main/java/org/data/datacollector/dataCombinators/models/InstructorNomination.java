package org.data.datacollector.dataCombinators.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorNomination {
    private String id;
    private String status;
    private String date;
    private String instructorId;
    private String instructorNumber;
}
