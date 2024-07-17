package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.MissedUser;
import org.data.datacollector.dataExtractors.dataHolders.DataOfMissedUser;
import org.data.datacollector.dataExtractors.extractors.MissedUsersDataExtractor;
import org.data.datacollector.dataExtractors.extractors.SuiviTRITREDataExtractor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class MissedUserDataCombinator extends DataCombinator {

    private final MissedUsersDataExtractor missedUsersDataExtractor;

    private final SuiviTRITREDataExtractor suiviTRITREDataExtractor;

    private Long START_ID = 7106L;

    public List<MissedUser> getMissedUserList() {
        List<DataOfMissedUser> dataOfMissedUsers = missedUsersDataExtractor.extractDataOfMissedUsers();
        return generateMissedUserList(dataOfMissedUsers);
    }

    private List<MissedUser> generateMissedUserList(List<DataOfMissedUser> dataOfMissedUsers) {
        return dataOfMissedUsers.stream()
                .map(dataOfMissedUser -> MissedUser.builder()
                        .id(String.valueOf(START_ID++))
                        .username("NULL")
                        .personalType("PNT")
                        .employeeNumber(dataOfMissedUser.getEmployeeNumber())
                        .lastName(dataOfMissedUser.getLastName())
                        .firstName(dataOfMissedUser.getFirstName())
                        .cFunction(dataOfMissedUser.getCFunction())
                        .airPlaneSpecialty(dataOfMissedUser.getAirPlaneSpecialty())
                        .email(dataOfMissedUser.getEmail())
                        .tel1(dataOfMissedUser.getTel1())
                        .nationality(dataOfMissedUser.getNationality())
                        .birthPlace(dataOfMissedUser.getBirthPlace())
                        .address(dataOfMissedUser.getAddress())
                        .gender(dataOfMissedUser.getGender())
                        .license(dataOfMissedUser.getLicense())
                        .passportNumber(dataOfMissedUser.getPassportNumber())
                        .passportValidityDate(dataOfMissedUser.getPassportValidityDate())
                        .isSIE(
                                suiviTRITREDataExtractor.extractEmployeeNumberOnlyWithSieDuAndSieAuFull()
                                        .contains(dataOfMissedUser.getEmployeeNumber()) ? "1" : "0"
                        )
                        .build()
                )
                .toList();
    }
}
