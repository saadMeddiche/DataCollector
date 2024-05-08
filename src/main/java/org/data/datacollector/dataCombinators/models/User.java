package org.data.datacollector.dataCombinators.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;

    private String username;
    private String firstName;
    private String lastName;

    private String employeeNumber; // matricule

    private String cFunction;

    private String personalType; //PNT ou PNT ..etc

    private String airplaneSpecialty;

    private String releaseDate;
    private String releasedByEmployeeNumber;
}
