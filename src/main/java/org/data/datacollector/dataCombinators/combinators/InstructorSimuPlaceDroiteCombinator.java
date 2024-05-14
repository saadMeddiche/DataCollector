package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.InstructorSimuPlaceDroite;
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
public class InstructorSimuPlaceDroiteCombinator extends DataCombinator {

    private final SuiviTRITREDataExtractor suiviTRITREDataExtractor;

    private final ListeIPLPNTDataExtractor listeIPLPNTDataExtractor;

    private Long START_ID = 1L;

    public final List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteWithoutInstructorIdList =  new ArrayList<>();

    public final List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteWithoutSieNumberList = new ArrayList<>();

    public final List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteWithoutSieIdList =  new ArrayList<>();

    public List<InstructorSimuPlaceDroite> getInstructorSimuPlaceDroiteList(){
        List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteList = generateInstructorSimuPlaceDroite(suiviTRITREDataExtractor.extractSuiviTRITRE());
        instructorSimuPlaceDroiteList = attachInstructionIdToInstructorSimuPlaceDroiteList(
                instructorSimuPlaceDroiteList, suiviTRITREDataExtractor.extractEmployeeNumberAndUserId()
        );
        instructorSimuPlaceDroiteList = attachSieNumberToInstructorSimuPlaceDroiteList(
                instructorSimuPlaceDroiteList, listeIPLPNTDataExtractor.extractListeIPLPNT()
        );
        return attachSieIdToInstructorSimuPlaceDroiteList(
                instructorSimuPlaceDroiteList, suiviTRITREDataExtractor.extractEmployeeNumberAndUserId()
        );
    }

    private List<InstructorSimuPlaceDroite> generateInstructorSimuPlaceDroite(List<SuiviTRITRE> suiviTRITREList){
        return suiviTRITREList.stream()
                .filter(suiviTRITRE -> suiviTRITRE.getDateSimuPlaceDroite() != null && suiviTRITRE.getTreSimuPlaceDroite() != null)
                .map(
                suiviTRITRE -> InstructorSimuPlaceDroite.builder()
                            .id(String.valueOf(START_ID++))
                            .date(dateBuilder(suiviTRITRE.getDateSimuPlaceDroite()))
                            .instructorNumber(suiviTRITRE.getEmployeeNumber())
                            .sieName(suiviTRITRE.getTreSimuPlaceDroite())
                        .build()
        ).toList();
    }

    private List<InstructorSimuPlaceDroite> attachInstructionIdToInstructorSimuPlaceDroiteList(List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return instructorSimuPlaceDroiteList.stream().peek(instructorSimuPlaceDroite -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(instructorSimuPlaceDroite.getInstructorNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> instructorSimuPlaceDroite.setInstructorId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> instructorSimuPlaceDroiteWithoutInstructorIdList.add(instructorSimuPlaceDroite)
                )
        ).toList();
    }

    private List<InstructorSimuPlaceDroite> attachSieNumberToInstructorSimuPlaceDroiteList(List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteList, List<ListeIPLPNT> ListeIPLPNT){
        return instructorSimuPlaceDroiteList.stream().peek(instructorSimuPlaceDroite -> ListeIPLPNT.stream()
                .filter(IPLPNT -> IPLPNT.getEmployeeName().equals(instructorSimuPlaceDroite.getSieName()))
                .findFirst()
                .ifPresentOrElse(
                        IPLPNT -> instructorSimuPlaceDroite.setSieNumber(IPLPNT.getEmployeeNumber())
                        ,
                        () -> instructorSimuPlaceDroiteWithoutSieNumberList.add(instructorSimuPlaceDroite)
                )
        ).toList();
    }

    private List<InstructorSimuPlaceDroite> attachSieIdToInstructorSimuPlaceDroiteList(List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return instructorSimuPlaceDroiteList.stream().peek(instructorSimuPlaceDroite -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(instructorSimuPlaceDroite.getSieNumber()))
                .findFirst()
                .ifPresentOrElse(
                        employeeNumberAndUserId -> instructorSimuPlaceDroite.setSieId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> instructorSimuPlaceDroiteWithoutSieIdList.add(instructorSimuPlaceDroite)
                )
        ).toList();
    }
}
