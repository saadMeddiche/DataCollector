package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.User;
import org.data.datacollector.dataExtractors.extractors.SuiviTRITREDataExtractor;
import org.data.datacollector.dataExtractors.extractors.UserDataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromPERSO.UserPERSO;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUTETPF.UserUTETPF;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class UserDataCombinator extends DataCombinator {

    private final UserDataExtractor userDataExtractor;

    private final SuiviTRITREDataExtractor suiviTRITREDataExtractor;

    private Long START_ID = 7052L;

    public List<User> getUserList() {

        List<UserPERSO> userPERSOList = userDataExtractor.extractUserPERSO();
        List<UserUTETPF> userUTETPFList = userDataExtractor.extractUserUTETPF();

        List<UserPERSO> filtredUserPERSOList = removeUsersThatExistInList(userPERSOList, userUTETPFList);

       return generateUserList(filtredUserPERSOList);
    }

    private List<UserPERSO> removeUsersThatExistInList(List<UserPERSO> userPERSOList, List<UserUTETPF> usersThatExistInThePERSOFile){
        return userPERSOList.stream()
                .filter(user -> usersThatExistInThePERSOFile.stream().noneMatch(userUTETPF -> userUTETPF.getEmployeeNumber().equals(user.getEmployeeNumber())))
                .toList();
    }

    private List<User> generateUserList(List<UserPERSO> userPERSOList) {
        return userPERSOList.stream()
                .map(userPERSO -> User.builder()
                        .id(String.valueOf(START_ID++))
                        .username("NULL")
                        .personalType("PNT")
                        .employeeNumber(userPERSO.getEmployeeNumber())
                        .firstName(userPERSO.getFirstName())
                        .lastName(userPERSO.getLastName())
                        .cFunction(userPERSO.getCFunction())
                        .airplaneSpecialty(userPERSO.getAirplaneSpecialty())
                        .releaseDate(dateBuilder(userPERSO.getReleaseDate()))
                        .releasedByEmployeeNumber(userPERSO.getReleasedByEmployeeNumber())
                        .isSIE(
                                suiviTRITREDataExtractor.extractEmployeeNumberOnlyWithSieDuAndSieAuFull()
                                        .contains(userPERSO.getEmployeeNumber()) ? "1" : "0"
                        )
                        .build()
                ).toList();
    }

}
