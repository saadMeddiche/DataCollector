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

    public List<Butee> getButeeList(){
        List<Butee> buteeList = new ArrayList<>();

        buteeList.addAll(getCHLButee());

        buteeList.addAll(getSCButee());

        return buteeList;
    }

    private List<Butee> getCHLButee(){
        List<Butee> buteeList = generateCHLButee();
        return attachUserIdToButee(buteeList , dataExtractor.employeeNumberAndUserIdList);
    }

    private List<Butee> getSCButee(){
        List<Butee> buteeList = generateSCButee();
        return attachUserIdToButee(buteeList , dataExtractor.employeeNumberAndUserIdList);
    }

    // Butee with jeppesenCode = SC
    private  List<Butee> generateSCButee(){
        return generateButee(dataExtractor.buteeSCList, "SC");
    }

    // Butee with jeppesenCode = CHL
    private List<Butee> generateCHLButee(){
        return generateButee(dataExtractor.buteeSimuList, "CHL");
    }

    private List<Butee> attachUserIdToButee(List<Butee> buteeList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
       return buteeList.stream().peek(butee -> {
            employeeNumberAndUserIdList.stream()
                    .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(butee.getEmployeeNumber()))
                    .findFirst()
                    .ifPresent(employeeNumberAndUserId -> butee.setId(employeeNumberAndUserId.getUserId()));
        }).toList();
    }

    private List<Butee> generateButee(List<? extends ButeeData> buteeDataList, String jeppesenCode) {
        List<Butee> buteeList = new ArrayList<>();

        buteeDataList.forEach(buteeData -> {
            Long employeeNumber = buteeData.getEmployeeNumber();
            buteeData.getValidityEnds().forEach(validityEnd -> {
                Butee butee = Butee.builder()
                        .employeeNumber(employeeNumber)
                        .jeppesenCode(jeppesenCode)
                        .validityEnd(validityEnd)
                        .build();
                buteeList.add(butee);
            });
        });

        return buteeList;
    }


}
