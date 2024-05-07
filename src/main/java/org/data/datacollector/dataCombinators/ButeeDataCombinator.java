package org.data.datacollector.dataCombinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.Butee;
import org.data.datacollector.dataExtractors.ButeeDataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.global.ButeeData;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ButeeDataCombinator extends DataCombinator {

    private final ButeeDataExtractor buteeDataExtractor;

    private Long START_ID = 1L;

    public List<Butee> buteeWithoutUserIdList = new ArrayList<>();

    public List<Butee> getButeeList(){
        List<Butee> buteeList = new ArrayList<>();

        buteeList.addAll(generateCHLButee());

        buteeList.addAll(generateSCButee());

        buteeList.addAll(generateDGButee());

        buteeList.addAll(generateSSButee());

        buteeList.addAll(generateCtrlELButee());

        buteeList.addAll(generateCRMButee());

        buteeList.addAll(generateELPButee());

        return attachUserIdToButee(buteeList , buteeDataExtractor.extractEmployeeNumberAndUserId());
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

    // Butee with jeppesenCode = CEL
    private List<Butee> generateCtrlELButee(){
        return generateButee(buteeDataExtractor.extractButeeCtrlEL(), "CEL");
    }

    // Butee with jeppesenCode = CRM
    private List<Butee> generateCRMButee(){
        return generateButee(buteeDataExtractor.extractButeeCRM(), "HF");
    }

    // Butte with jeppesenCode = EA
    private List<Butee> generateELPButee(){
        return generateButee(buteeDataExtractor.extractButeeELP(), "EA");
    }
    private List<Butee> attachUserIdToButee(List<Butee> buteeList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
       return buteeList.stream().peek(butee -> employeeNumberAndUserIdList.stream()
               .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(butee.getEmployeeNumber()))
               .findFirst()
               .ifPresentOrElse(
                       (employeeNumberAndUserId) -> butee.setUserId(employeeNumberAndUserId.getUserId())
                       ,
                       () -> buteeWithoutUserIdList.add(butee)
                       )
       ).toList();
    }

    private List<Butee> generateButee(List<? extends ButeeData> buteeDataList, String jeppesenCode) {
        return buteeDataList.stream()
                .flatMap(buteeData -> buteeData.getValidityEnds().stream()
                        .map(validityEnd -> Butee.builder()
                                .id(String.valueOf(START_ID++))
                                .employeeNumber(buteeData.getEmployeeNumber())
                                .jeppesenCode(jeppesenCode)
                                .validityEnd(dateBuilder(validityEnd))
                                .build()))
                .toList();
    }

}
