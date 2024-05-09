package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightResult {
    private String id;
    private String lineCheckId;
    private String dateOfOrigin;
    private String carrier;
    private String flightNumber;
    private String startStation;
    private String endStation;
    private String aircraftType;
}