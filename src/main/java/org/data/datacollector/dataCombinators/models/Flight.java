package org.data.datacollector.dataCombinators.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String id;
    private String dateOfOrigin;
    private String lineCheckId;
    private String instructorNumber;
}
