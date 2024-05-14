package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.InstructorObservation;
import org.data.datacollector.dataExtractors.dataHolders.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.dataHolders.ListeIPLPNT;
import org.data.datacollector.dataExtractors.dataHolders.SuiviTRITRE;
import org.data.datacollector.dataExtractors.extractors.ListeIPLPNTDataExtractor;
import org.data.datacollector.dataExtractors.extractors.SuiviTRITREDataExtractor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class InstructorObservationCombinator extends DataCombinator {

    private final SuiviTRITREDataExtractor suiviTRITREDataExtractor;

    private final ListeIPLPNTDataExtractor listeIPLPNTDataExtractor;
    public final List<InstructorObservation> instructorObservationWithoutInstructorIdList =  new ArrayList<>();

    public final List<InstructorObservation> instructorObservationWithoutSieNumberList = new ArrayList<>();

    public final List<InstructorObservation> instructorObservationWithoutSieIdList=  new ArrayList<>();
    private Long START_ID = 1L;

    public List<InstructorObservation> getInstructorObservationList(){
        List<InstructorObservation> instructorObservationList = generateInstructorObservation(suiviTRITREDataExtractor.extractSuiviTRITRE());
        instructorObservationList = attachInstructionIdToInstructorNominationList(
                instructorObservationList , suiviTRITREDataExtractor.extractEmployeeNumberAndUserId()
        );
        instructorObservationList = attachSieNumberToInstructorNominationList(
                instructorObservationList , listeIPLPNTDataExtractor.extractListeIPLPNT()
        );
        return attachSieIdToInstructorNominationList(
                instructorObservationList , suiviTRITREDataExtractor.extractEmployeeNumberAndUserId()
        );
    }

    private List<InstructorObservation> generateInstructorObservation(List<SuiviTRITRE> suiviTRITREList){
        return suiviTRITREList.stream()
                .filter(suiviTRITRE -> suiviTRITRE.getDateHabilitation() != null && suiviTRITRE.getSieHabilitation() != null)
                .map(
                suiviTRITRE -> InstructorObservation.builder()
                            .id(String.valueOf(START_ID++))
                            .type("Habilitation")
                            .date(dateBuilder(suiviTRITRE.getDateHabilitation()))
                            .instructorNumber(suiviTRITRE.getEmployeeNumber())
                            .sieName(suiviTRITRE.getSieHabilitation())
                        .build()
        ).toList();
    }

    private List<InstructorObservation> attachInstructionIdToInstructorNominationList(List<InstructorObservation> instructorObservationList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return instructorObservationList.stream().peek(instructorObservation -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(instructorObservation.getInstructorNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> instructorObservation.setInstructorId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> instructorObservationWithoutInstructorIdList.add(instructorObservation)
                )
        ).toList();
    }

    private List<InstructorObservation> attachSieNumberToInstructorNominationList(List<InstructorObservation> instructorObservationList, List<ListeIPLPNT> ListeIPLPNT){
        return instructorObservationList.stream().peek(instructorObservation -> ListeIPLPNT.stream()
                .filter(IPLPNT -> IPLPNT.getEmployeeName().equals(instructorObservation.getSieName()))
                .findFirst()
                .ifPresentOrElse(
                        IPLPNT -> instructorObservation.setSieNumber(IPLPNT.getEmployeeNumber())
                        ,
                        () -> instructorObservationWithoutSieNumberList.add(instructorObservation)
                )
        ).toList();
    }

    private List<InstructorObservation> attachSieIdToInstructorNominationList(List<InstructorObservation> instructorObservationList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return instructorObservationList.stream().peek(instructorObservation -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(instructorObservation.getSieNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> instructorObservation.setSieId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> instructorObservationWithoutSieIdList.add(instructorObservation)
                )
        ).toList();
    }

}
