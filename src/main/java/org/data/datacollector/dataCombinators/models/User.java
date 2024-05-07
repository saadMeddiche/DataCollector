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

    private String employeeNumber; // matricule

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String nationality;

    private String status;

    private String cFunction;

    private String tel1;

    private String tel2;

    private String email;

    private String address;

    private String city;

    private String birthDate;

    private String birthPlace;

    private String companyJoiningDate;

    private String familyStatus;

    private String gender;

    private String flightSector;

    private String passportNumber;

    private String passportValidityDate;

    private String personalType; //PNT ou PNT ..etc

    private String cin;

    private String jobId;

    private String organizationUnit;

    private String releaseDate;

    private String releasedByEmployeeNumber;

    private String seniorityDate;

    private String seniority;

    private String employeesManagerNumber;

    private String airplaneSpecialty;

    private String licence;

    private String licenceValidityEnd;

    private String dacAutNumber;

    private String dacAutValidityEnd;

    private String isSIE;

    private String nextMedicalExaminationDate;

    private String photoLocation;

    private String isPN;

    private String isTemporaryInstructor; //instructeur vacataire : soit retraité ou externe...

    private String image;

    private String isJeppesenUpdated;

    private String electronicSignature;

    private String createdDate;

    private String lastModifiedDate;
}
