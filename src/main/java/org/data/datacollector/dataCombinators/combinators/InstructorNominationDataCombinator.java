package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.InstructorNomination;
import org.data.datacollector.dataExtractors.dataHolders.SuiviTRITRE;
import org.data.datacollector.dataExtractors.dataHolders.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.extractors.SuiviTRITREDataExtractor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class InstructorNominationDataCombinator extends DataCombinator {

    private final SuiviTRITREDataExtractor suiviTRITREDataExtractor;

    private Long START_ID = 1L;

    public List<InstructorNomination> instructorNominationWithoutInstructorIdList =  new ArrayList<>();

    public List<InstructorNomination> getInstructorNominationList(){
        List<InstructorNomination> instructorNominationList = generateInstructorNomination(suiviTRITREDataExtractor.extractSuiviTRITRE());
        return attachInstructionIdToInstructorNominationList(
                instructorNominationList , suiviTRITREDataExtractor.extractEmployeeNumberAndUserId()
        );
    }

    private List<InstructorNomination> generateInstructorNomination(List<SuiviTRITRE> suiviTRITREList){
        return suiviTRITREList.stream().map(
                suiviTRITRE -> InstructorNomination.builder()
                            .id(String.valueOf(START_ID++))
                            .status(statusBuilder(suiviTRITRE.getNominationStatus()))
                            .date(dateBuilder(suiviTRITRE.getNominationDate()))
                            .instructorNumber(suiviTRITRE.getEmployeeNumber())
                        .build()
        ).toList();
    }

    private String statusBuilder(String status){
        return "oui".equalsIgnoreCase(status) ? "1" : "0";
    }

    private List<InstructorNomination> attachInstructionIdToInstructorNominationList(List<InstructorNomination> instructorNominationList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return instructorNominationList.stream().peek(instructorNomination -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(instructorNomination.getInstructorNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> instructorNomination.setInstructorId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> instructorNominationWithoutInstructorIdList.add(instructorNomination)
                )
        ).toList();
    }
}
