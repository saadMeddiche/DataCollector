package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.LineCheck;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.CtrlEL;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.extractors.CtrlELDataExtractor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LineCheckDataCombinator extends DataCombinator {

    private final CtrlELDataExtractor ctrlELDataExtractor;

    private Long START_ID = 1L;

    public List<LineCheck> lineCheckWithoutInstructorIdList = new ArrayList<>();


    public List<LineCheck> getLineCheckList(){
        List<LineCheck> lineCheckList = generateLineCheck(ctrlELDataExtractor.extractCtrlEL());
        return attachInstructorIdToLineCheck(lineCheckList , ctrlELDataExtractor.extractEmployeeNumberAndUserId());
    }

    private List<LineCheck> generateLineCheck(List<CtrlEL> ctrlELList){
        return ctrlELList.stream().flatMap(
                ctrlEL -> ctrlEL.getRows().stream()
                .filter(row -> row.getStartDate() != null && row.getInstructorNumber() != null)
                .map(row -> LineCheck.builder()
                .id(String.valueOf(START_ID++))
                .dateOfOrigin(dateBuilder(row.getStartDate()))
                .presenceMarked("1")
                .instructorNumber(row.getInstructorNumber())
                .build()
        )).toList();
    }

    private List<LineCheck> attachInstructorIdToLineCheck(List<LineCheck> lineCheckList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return lineCheckList.stream().peek(lineCheck -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(lineCheck.getInstructorNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> lineCheck.setInstructorId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> lineCheckWithoutInstructorIdList.add(lineCheck)
                )
        ).toList();
    }
}
