package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineCheckResult {

    private String id;

    private String dateOfOrigin;

    private String presenceMarked;

    private String instructorId;

}
