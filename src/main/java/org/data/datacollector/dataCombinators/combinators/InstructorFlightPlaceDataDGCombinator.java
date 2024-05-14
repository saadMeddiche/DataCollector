package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.InstructorFlightPlaceDg;
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
public class InstructorFlightPlaceDataDGCombinator extends DataCombinator {

    private final SuiviTRITREDataExtractor suiviTRITREDataExtractor;

    private final ListeIPLPNTDataExtractor listeIPLPNTDataExtractor;

    private Long START_ID = 1L;

    public final List<InstructorFlightPlaceDg> instructorFlightPlaceDgWithoutInstructorIdList =  new ArrayList<>();

    public final List<InstructorFlightPlaceDg> instructorFlightPlaceDgWithoutSieNumberList = new ArrayList<>();

    public final List<InstructorFlightPlaceDg> instructorFlightPlaceDgWithoutSieIdList=  new ArrayList<>();

    public List<InstructorFlightPlaceDg> getInstructorFlightPlaceDgList(){
        List<InstructorFlightPlaceDg> instructorFlightPlaceDgList = generateInstructorFlightPlaceDg(suiviTRITREDataExtractor.extractSuiviTRITRE());
        instructorFlightPlaceDgList = attachInstructionIdToInstructorFlightPlaceDgList(
                instructorFlightPlaceDgList , suiviTRITREDataExtractor.extractEmployeeNumberAndUserId()
        );
        instructorFlightPlaceDgList = attachSieNumberToInstructorFlightPlaceDgList(
                instructorFlightPlaceDgList , listeIPLPNTDataExtractor.extractListeIPLPNT()
        );
        return attachSieIdToInstructorFlightPlaceDgList(
                instructorFlightPlaceDgList , suiviTRITREDataExtractor.extractEmployeeNumberAndUserId()
        );
    }

    private List<InstructorFlightPlaceDg> generateInstructorFlightPlaceDg(List<SuiviTRITRE> suiviTRITREList){
        return suiviTRITREList.stream()
                .filter(suiviTRITRE -> suiviTRITRE.getDateSimuPlaceDroite() != null && suiviTRITRE.getTreSimuPlaceDroite() != null)
                .map(
                suiviTRITRE -> InstructorFlightPlaceDg.builder()
                            .id(String.valueOf(START_ID++))
                            .date(dateBuilder(suiviTRITRE.getDateSimuPlaceDroite()))
                            .instructorNumber(suiviTRITRE.getEmployeeNumber())
                            .sieName(suiviTRITRE.getTreSimuPlaceDroite())
                        .build()
        ).toList();
    }

    private List<InstructorFlightPlaceDg> attachInstructionIdToInstructorFlightPlaceDgList(List<InstructorFlightPlaceDg> instructorFlightPlaceDgList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return instructorFlightPlaceDgList.stream().peek(instructorFlightPlaceDg -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(instructorFlightPlaceDg.getInstructorNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> instructorFlightPlaceDg.setInstructorId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> instructorFlightPlaceDgWithoutInstructorIdList.add(instructorFlightPlaceDg)
                )
        ).toList();
    }

    private List<InstructorFlightPlaceDg> attachSieNumberToInstructorFlightPlaceDgList(List<InstructorFlightPlaceDg> instructorFlightPlaceDgList, List<ListeIPLPNT> ListeIPLPNT){
        return instructorFlightPlaceDgList.stream().peek(instructorFlightPlaceDg -> ListeIPLPNT.stream()
                .filter(IPLPNT -> IPLPNT.getEmployeeName().equals(instructorFlightPlaceDg.getSieName()))
                .findFirst()
                .ifPresentOrElse(
                        IPLPNT -> instructorFlightPlaceDg.setSieNumber(IPLPNT.getEmployeeNumber())
                        ,
                        () -> instructorFlightPlaceDgWithoutSieNumberList.add(instructorFlightPlaceDg)
                )
        ).toList();
    }

    private List<InstructorFlightPlaceDg> attachSieIdToInstructorFlightPlaceDgList(List<InstructorFlightPlaceDg> instructorFlightPlaceDgList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return instructorFlightPlaceDgList.stream().peek(instructorFlightPlaceDg -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(instructorFlightPlaceDg.getSieNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> instructorFlightPlaceDg.setSieId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> instructorFlightPlaceDgWithoutSieIdList.add(instructorFlightPlaceDg)
                )
        ).toList();
    }
}
