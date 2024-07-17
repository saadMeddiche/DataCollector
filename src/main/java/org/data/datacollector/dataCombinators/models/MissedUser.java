package org.data.datacollector.dataCombinators.models;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class MissedUser {


    private String id;

    private String personalType;

    private String isSIE;

    private String employeeNumber;

    private String username;

    private String lastName;

    private String firstName;

    private String cFunction;

    private String airPlaneSpecialty;

    private String email;

    private String tel1;

    private String nationality;

    private String birthPlace;

    private String address;

    private String gender;

    private String license;

    private String passportNumber;

    private String passportValidityDate;


}
