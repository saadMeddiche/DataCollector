package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenceResult {

    // Field With Values
    private String id;
    private String validTo;
    private String examinerEmployeeNumber;
    private String examinerName;
    private String userId;

    // Field Without Values
    private String type;
    private String issueDate;
    private String chlThatImpactedIrButeeDate;
    private String chlThatImpactedIrButeeId;
}
