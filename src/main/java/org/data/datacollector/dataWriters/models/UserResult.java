package org.data.datacollector.dataWriters.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResult {

    private String id;
    private String address;
    private String airplaneSpecialty;
    private String birthDate;
    private String birthPlace;
    private String cFunction;
    private String cin;
    private String city;
    private String companyJoiningDate;
    //    private String createdDate; // nope
    private String dacAutNumber;
    private String dacAutValidityEnd;
    //    private String electronicSignature; // nope
    private String email;
    private String employeeNumber; // matricule
    private String employeesManagerNumber;
    private String familyStatus;
    private String firstName;
    private String flightSector;
    private String gender;
    private String image;
    //    private String isJeppesenUpdated; // nope
    private String isPN;
    private String isSIE;
    private String isTemporaryInstructor; //instructeur vacataire : soit retraité ou externe...
    private String jobId;
    //    private String lastModifiedDate; // nope
    private String lastName;
    private String licence;
    private String licenceValidityEnd;
    private String nationality;
    private String nextMedicalExaminationDate;
    private String organizationUnit;
    private String passportNumber;
    private String passportValidityDate;
    private String password;
    private String personalType; //PNT ou PNT ..etc
    private String photoLocation;
    private String releaseDate;
    private String releasedByEmployeeNumber;
    private String seniority;
    private String seniorityDate;
    private String tel1;
    private String tel2;
    private String username;
    private String status;
}
