package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatResult {
    private String id;
    private String type;
    private String category;
    private String inst;
    private String date;
    private String pntId;
    private String instructorId;
}
