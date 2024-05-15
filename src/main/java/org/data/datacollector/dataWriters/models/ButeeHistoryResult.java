package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButeeHistoryResult {

    // Field With Values
    private String id;
    private String jeppesenCode;
    private String validityEnd;
    private String userId;

    // Field Without Values
    private String category;
    private String validityStart;
    private String trainingId;

}

