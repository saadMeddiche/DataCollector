package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnknownUserResult {

    private String employeeNumber;

    private String firstName;

    private String lastName;

    private String sheetName;
}
