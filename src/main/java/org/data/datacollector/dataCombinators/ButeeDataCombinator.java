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

        return attachUserIdToButee(buteeList , dataExtractor.employeeNumberAndUserIdList);
    }

    // Butee with jeppesenCode = CHL
    private List<Butee> generateCHLButee(){
        return generateButee(dataExtractor.buteeSimuList, "CHL");
    }

    // Butee with jeppesenCode = SC
    private  List<Butee> generateSCButee(){
        return generateButee(dataExtractor.buteeSCList, "SC");
    }

    // Butte with jeppesenCode = DG
    private List<Butee> generateDGButee(){
        return generateButee(dataExtractor.buteeDGList, "DG");
    }

    // Butee with jeppesenCode = SS
    private List<Butee> generateSSButee(){
        return generateButee(dataExtractor.buteeSSList, "SS");
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
