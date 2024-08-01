package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.Butee;
import org.data.datacollector.dataExtractors.dataHolders.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.extractors.ButeeHistoryDataExtractor;
import org.data.datacollector.dataExtractors.globalDataHolders.ButeeData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class ButeeDataCombinator extends DataCombinator {

    private final ButeeHistoryDataExtractor buteeDataExtractor;

    private Long START_ID = 1L;

    public List<Butee> buteeWithoutUserIdList = new ArrayList<>();

    public List<Butee> getButeeList(){
        List<Butee> buteeList = new ArrayList<>();

        buteeList.addAll(generateCHLButee());

        buteeList.addAll(generateSCButee());

        buteeList.addAll(generateDGButee());

        buteeList.addAll(generateSSButee());

        buteeList.addAll(generateCRMButee());

        buteeList.addAll(generateELPButee());

        buteeList.addAll(generateCtrlELButee());

        return attachUserIdToButee(buteeList, buteeDataExtractor.extractEmployeeNumberAndUserId());
    }

    // Butee with jeppesenCode = CHL
    private List<Butee> generateCHLButee(){
        return generateButee(buteeDataExtractor.extractButeeSimu(), "CHL");
    }

    // Butee with jeppesenCode = SC
    private  List<Butee> generateSCButee(){
        return generateButee(buteeDataExtractor.extractButeeSC(), "SC");
    }

    // Butte with jeppesenCode = DG
    private List<Butee> generateDGButee(){
        return generateButee(buteeDataExtractor.extractButeeDG(), "DG");
    }

    // Butee with jeppesenCode = SS
    private List<Butee> generateSSButee(){
        return generateButee(buteeDataExtractor.extractButeeSS(), "SS");
    }

    // Butee with jeppesenCode = CRM
    private List<Butee> generateCRMButee(){
        return generateButee(buteeDataExtractor.extractButeeCRM(), "HF");
    }

    // Butte with jeppesenCode = EA
    private List<Butee> generateELPButee(){
        return generateButee(buteeDataExtractor.extractButeeELP(), "EA");
    }

    // Butee with jeppesenCode = CEL
    private List<Butee> generateCtrlELButee(){
        return generateButee(buteeDataExtractor.extractButeeCtrlEL(), "CEL");
    }

    private List<Butee> attachUserIdToButee(List<Butee> buteeList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
       return buteeList.stream().peek(butee -> employeeNumberAndUserIdList.stream()
               .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(butee.getUserNumber()))
               .findFirst()
               .ifPresentOrElse(
                       (employeeNumberAndUserId) -> butee.setUserId(employeeNumberAndUserId.getUserId())
                       ,
                       () -> buteeWithoutUserIdList.add(butee)
                       )
       ).filter(butee -> butee.getUserId() != null && !butee.getUserId().isEmpty()).toList();
    }

    private List<Butee> generateButee(List<? extends ButeeData> buteeDataList, String jeppesenCode) {
        return buteeDataList.stream()
                .filter(buteeData -> !buteeData.getValidityEnds().isEmpty())
                .map(buteeData -> Butee.builder()
                            .id(String.valueOf(START_ID++))
                            .jeppesenCode(jeppesenCode)
                            .userNumber(buteeData.getEmployeeNumber())
                            .validityEnd(dateBuilder(buteeData.getValidityEnds().getLast()))
                            .build()
                )
                .toList();
    }

}
