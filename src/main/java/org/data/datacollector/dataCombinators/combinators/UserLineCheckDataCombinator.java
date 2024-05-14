package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.LineCheck;
import org.data.datacollector.dataCombinators.models.UserLineCheck;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.CtrlEL;
import org.data.datacollector.dataExtractors.dataHolders.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.extractors.CtrlELDataExtractor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class UserLineCheckDataCombinator extends DataCombinator {

    private final CtrlELDataExtractor ctrlELDataExtractor;

    private final LineCheckDataCombinator lineCheckDataCombinator;

    public List<UserLineCheck> userLineCheckWithoutTraineeIdList = new ArrayList<>();

    public List<UserLineCheck> userLineCheckWithoutLineCheckIdList = new ArrayList<>();

    private Long START_ID = 1L;

    public List<UserLineCheck> getUserLineCheckList(){
        List<UserLineCheck> userLineCheckList = generateUserLineCheck(ctrlELDataExtractor.extractCtrlEL());

        return attachLineCheckIdToUserLineCheck(
                attachTraineeIdToUserLineCheck(
                        userLineCheckList,
                        ctrlELDataExtractor.extractEmployeeNumberAndUserId()
                ),
                lineCheckDataCombinator.getLineCheckList()
        );
    }

    private List<UserLineCheck> generateUserLineCheck(List<CtrlEL> ctrlELList){
        return ctrlELList.stream().flatMap(
                ctrlEL -> ctrlEL.getRows().stream()
                .filter(row -> row.getStartDate() != null && row.getInstructorNumber() != null)
                .map(row -> UserLineCheck.builder()
                        .id(String.valueOf(START_ID++))
                        .oldButeeDate(dateBuilder(row.getButeeDate()))
                        .traineeNumber(ctrlEL.getEmployeeNumber())
                        .instructorNumber(row.getInstructorNumber())
                        .dateOfOrigin(dateBuilder(row.getStartDate()))
                        .isLongAbsence("0")
                        .isLongAbsenceControl("0")
                        .isPresent("1")
                        .moringPresence("0")
                        .afternoonPresence("0")
                    .build()
                )
        ).toList();
    }

    private List<UserLineCheck> attachTraineeIdToUserLineCheck(List<UserLineCheck> userLineCheckList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return userLineCheckList.stream().peek(userLineCheck -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(userLineCheck.getTraineeNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> userLineCheck.setTraineeId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> userLineCheckWithoutTraineeIdList.add(userLineCheck)
                )
        ).toList();
    }

    private List<UserLineCheck> attachLineCheckIdToUserLineCheck(List<UserLineCheck> userLineCheckList , List<LineCheck> lineCheckList){
        return userLineCheckList.stream().peek(userLineCheck -> lineCheckList.stream()
                .filter(lineCheck -> lineCheck.getDateOfOrigin().equals(userLineCheck.getDateOfOrigin())
                        && lineCheck.getInstructorNumber().equals(userLineCheck.getInstructorNumber()))
                .findFirst()
                .ifPresentOrElse(
                        lineCheck -> userLineCheck.setLineCheckId(lineCheck.getId())
                        ,
                        () -> userLineCheckWithoutLineCheckIdList.add(userLineCheck)
                )
        ).toList();
    }


}
