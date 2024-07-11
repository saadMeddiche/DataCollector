package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.UnknownUser;
import org.data.datacollector.dataExtractors.dataHolders.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.extractors.UnknownUserDataExtractor;
import org.data.datacollector.dataExtractors.globalDataHolders.UnknownUserData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class UnknownUserDataCombinator {

    private final UnknownUserDataExtractor unknownUserDataExtractor;

    public List<UnknownUser> getUnknownUserList(){
        List<UnknownUser> unknownUserList = new ArrayList<>();
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserSimu() , "SIMU"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserEL() , "EL"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserCRM() , "CRM"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserSS() , "SS"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserDG() , "DG"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserSC() , "SC"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserCAT2() , "CAT 2,3"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserIR() , "IR"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserELP() , "ELP"));
        unknownUserList.addAll(generateUnknownUserList(unknownUserDataExtractor.extractUserSuiviTRITRE() , "Suivi TRITRE"));
        return unknownUserList;
    }

    private List<UnknownUser> generateUnknownUserList(List<? extends UnknownUserData> unknownUserDataList , String sheetName){

        List<EmployeeNumberAndUserId> employeeNumberAndUserIds = unknownUserDataExtractor.extractEmployeeNumberAndUserId();

        return unknownUserDataList.stream()
                .filter(unknownUserData -> employeeNumberAndUserIds.stream()
                        .noneMatch(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(unknownUserData.getEmployeeNumber())))
                .map(unknownUserData -> UnknownUser.builder()
                        .employeeNumber(unknownUserData.getEmployeeNumber())
                        .firstName(unknownUserData.getFirstName())
                        .lastName(unknownUserData.getLastName())
                        .sheetName(sheetName)
                        .build()
                ).toList();
    }
}
