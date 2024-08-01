package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.ButeeHistory;
import org.data.datacollector.dataExtractors.extractors.ButeeHistoryDataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.globalDataHolders.ButeeData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class ButeeHistoryDataCombinator extends DataCombinator {

    private final ButeeHistoryDataExtractor buteeHistoryDataExtractor;

    private Long START_ID = 1L;

    public List<ButeeHistory> buteeHistoryWithoutUserIdList = new ArrayList<>();

    public List<ButeeHistory> getButeeHistoryList(){
        List<ButeeHistory> buteeHistoryList = new ArrayList<>();

        buteeHistoryList.addAll(generateCHLButee());

        buteeHistoryList.addAll(generateSCButee());

        buteeHistoryList.addAll(generateDGButee());

        buteeHistoryList.addAll(generateSSButee());

        buteeHistoryList.addAll(generateCRMButee());

        buteeHistoryList.addAll(generateELPButee());

        buteeHistoryList.addAll(generateCtrlELButee());

        return attachUserIdToButee(buteeHistoryList, buteeHistoryDataExtractor.extractEmployeeNumberAndUserId());
    }

    // Butee with jeppesenCode = CHL
    private List<ButeeHistory> generateCHLButee(){
        return generateButee(buteeHistoryDataExtractor.extractButeeSimu(), "CHL");
    }

    // Butee with jeppesenCode = SC
    private  List<ButeeHistory> generateSCButee(){
        return generateButee(buteeHistoryDataExtractor.extractButeeSC(), "SC");
    }

    // Butte with jeppesenCode = DG
    private List<ButeeHistory> generateDGButee(){
        return generateButee(buteeHistoryDataExtractor.extractButeeDG(), "DG");
    }

    // Butee with jeppesenCode = SS
    private List<ButeeHistory> generateSSButee(){
        return generateButee(buteeHistoryDataExtractor.extractButeeSS(), "SS");
    }

    // Butee with jeppesenCode = CEL
    private List<ButeeHistory> generateCtrlELButee(){
        return generateButee(buteeHistoryDataExtractor.extractButeeCtrlEL(), "CEL");
    }

    // Butee with jeppesenCode = CRM
    private List<ButeeHistory> generateCRMButee(){
        return generateButee(buteeHistoryDataExtractor.extractButeeCRM(), "HF");
    }

    // Butte with jeppesenCode = EA
    private List<ButeeHistory> generateELPButee(){
        return generateButee(buteeHistoryDataExtractor.extractButeeELP(), "EA");
    }
    private List<ButeeHistory> attachUserIdToButee(List<ButeeHistory> buteeHistoryList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
       return buteeHistoryList.stream().peek(buteeHistory -> employeeNumberAndUserIdList.stream()
               .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(buteeHistory.getUserNumber()))
               .findFirst()
               .ifPresentOrElse(
                       (employeeNumberAndUserId) -> buteeHistory.setUserId(employeeNumberAndUserId.getUserId())
                       ,
                       () -> buteeHistoryWithoutUserIdList.add(buteeHistory)
                       )
       ).toList();
    }

    private List<ButeeHistory> generateButee(List<? extends ButeeData> buteeDataList, String jeppesenCode) {
        return buteeDataList.stream()
                .flatMap(buteeData -> buteeData.getValidityEnds().stream()
                        .map(validityEnd -> ButeeHistory.builder()
                                .id(String.valueOf(START_ID++))
                                .userNumber(buteeData.getEmployeeNumber())
                                .jeppesenCode(jeppesenCode)
                                .validityEnd(dateBuilder(validityEnd))
                                .build()))
                .toList();
    }

}
