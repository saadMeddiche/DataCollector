package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DacAuthorisationResult {
    private String id;
    private String type;
    private String autorisationNumber;
    private String startDate;
    private String endDate;
    private String userId;
}
