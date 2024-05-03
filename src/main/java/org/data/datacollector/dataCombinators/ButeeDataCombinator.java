package org.data.datacollector.dataCombinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.Butee;
import org.data.datacollector.dataExtractor.DataExtractor;
import org.data.datacollector.dataExtractor.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractor.global.ButeeData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ButeeDataCombinator {

    private final DataExtractor dataExtractor;

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

        return attachUserIdToButee(buteeList , dataExtractor.extractEmployeeNumberAndUserId());
    }

    // Butee with jeppesenCode = CHL
    private List<Butee> generateCHLButee(){
        return generateButee(dataExtractor.extractButeeSimu(), "CHL");
    }

    // Butee with jeppesenCode = SC
    private  List<Butee> generateSCButee(){
        return generateButee(dataExtractor.extractButeeSC(), "SC");
    }

    // Butte with jeppesenCode = DG
    private List<Butee> generateDGButee(){
        return generateButee(dataExtractor.extractButeeDG(), "DG");
    }

    // Butee with jeppesenCode = SS
    private List<Butee> generateSSButee(){
        return generateButee(dataExtractor.extractButeeSS(), "SS");
    }

    // Butee with jeppesenCode = CEL
    private List<Butee> generateCtrlELButee(){
        return generateButee(dataExtractor.extractButeeCtrlEL(), "CEL");
    }

    // Butee with jeppesenCode = CRM
    private List<Butee> generateCRMButee(){
        return generateButee(dataExtractor.extractButeeCRM(), "HF");
    }

    // Butte with jeppesenCode = EA
    private List<Butee> generateELPButee(){
        return generateButee(dataExtractor.extractButeeELP(), "EA");
    }
    private List<Butee> attachUserIdToButee(List<Butee> buteeList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
       return buteeList.stream().peek(butee -> employeeNumberAndUserIdList.stream()
               .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(butee.getEmployeeNumber()))
               .findFirst()
               .ifPresentOrElse(
                       (employeeNumberAndUserId) -> butee.setUserIdFromDB(employeeNumberAndUserId.getUserId())
                       ,
                       () -> buteeWithoutUserIdList.add(butee)
                       )
       ).toList();
    }

    private List<Butee> generateButee(List<? extends ButeeData> buteeDataList, String jeppesenCode) {
        return buteeDataList.stream()
                .flatMap(buteeData -> buteeData.getValidityEnds().stream()
                        .map(validityEnd -> Butee.builder()
                                .employeeNumber(buteeData.getEmployeeNumber())
                                .jeppesenCode(jeppesenCode)
                                .validityEnd(validityEnd)
                                .build()))
                .toList();
    }


}
