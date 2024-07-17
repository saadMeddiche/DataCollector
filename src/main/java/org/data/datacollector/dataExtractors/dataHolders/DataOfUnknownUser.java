package org.data.datacollector.dataExtractors.dataHolders;

import com.poiji.annotation.ExcelCellName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DataOfUnknownUser {

    @ExcelCellName("Matricule")
    private String employeeNumber;

    @ExcelCellName("Nom")
    private String lastName;

    @ExcelCellName("Prénom")
    private String firstName;

    @ExcelCellName("Fonction")
    private String cFunction;

    @ExcelCellName("Machine")
    private String airPlaneSpecialty;

    @ExcelCellName("Email")
    private String email;

    @ExcelCellName("N° Téléphone")
    private String tel1;

    @ExcelCellName("Nationnalité")
    private String nationality;

    @ExcelCellName("Lieu de naissance")
    private String birthPlace;

    @ExcelCellName("Adresse")
    private String address;

    @ExcelCellName("genre")
    private String gender;

    @ExcelCellName("Numéro de licence")
    private String license;

    @ExcelCellName("N° passeport")
    private String passportNumber;

    @ExcelCellName("Validité Passeport")
    private String passportValidityDate;
}
