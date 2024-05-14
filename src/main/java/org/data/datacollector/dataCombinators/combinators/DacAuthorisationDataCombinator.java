package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.DacAuthorisation;
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
public class DacAuthorisationDataCombinator extends DataCombinator {

    private final SuiviTRITREDataExtractor suiviTRITREDataExtractor;
    private Long START_ID = 1L;

    public List<DacAuthorisation> dacAuthorisationWithoutUserIdList = new ArrayList<>();

    public List<DacAuthorisation> getDacAuthorisationList() {
        return attachUserIdsToDacAuthorisation(
                genarateDacAuthorisationList(suiviTRITREDataExtractor.extractSuiviTRITRE()),
                suiviTRITREDataExtractor.extractEmployeeNumberAndUserId()
        );
    }

    private List<DacAuthorisation> genarateDacAuthorisationList(List<SuiviTRITRE> suiviTRITREList) {

        List<DacAuthorisation> dacAuthorisationList = new ArrayList<>();

        suiviTRITREList.forEach(suiviTRITRE -> {

            if(suiviTRITRE.getAuthorisationNumberTRI() != null && suiviTRITRE.getStartDateTRI() != null && suiviTRITRE.getEndDateTRI() != null)
                dacAuthorisationList.add(
                        DacAuthorisation.builder()
                            .id(String.valueOf(START_ID++))
                            .type("TRI")
                            .autorisationNumber(suiviTRITRE.getAuthorisationNumberTRI())
                            .startDate(dateBuilder(suiviTRITRE.getStartDateTRI()))
                            .endDate(dateBuilder(suiviTRITRE.getEndDateTRI()))
                            .userNumber(suiviTRITRE.getEmployeeNumber())
                        .build()
                );

            if(suiviTRITRE.getAuthorisationNumberTRE() != null && suiviTRITRE.getStartDateTRE() != null && suiviTRITRE.getEndDateTRE() != null)
                dacAuthorisationList.add(
                        DacAuthorisation.builder()
                            .id(String.valueOf(START_ID++))
                            .type("TRE")
                            .autorisationNumber(suiviTRITRE.getAuthorisationNumberTRE())
                            .startDate(dateBuilder(suiviTRITRE.getStartDateTRE()))
                            .endDate(dateBuilder(suiviTRITRE.getEndDateTRE()))
                            .userNumber(suiviTRITRE.getEmployeeNumber())
                        .build()
                );

            if(suiviTRITRE.getStartDateFIATPL() != null && suiviTRITRE.getEndDateFIATPL() != null)
                dacAuthorisationList.add(
                        DacAuthorisation.builder()
                            .id(String.valueOf(START_ID++))
                            .type("FI_ATPL")
                            .startDate(dateBuilder(suiviTRITRE.getStartDateFIATPL()))
                            .endDate(dateBuilder(suiviTRITRE.getEndDateFIATPL()))
                            .userNumber(suiviTRITRE.getEmployeeNumber())
                        .build()
                );

        });

        return dacAuthorisationList;
    }

    private List<DacAuthorisation> attachUserIdsToDacAuthorisation(List<DacAuthorisation> dacAuthorisationList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return dacAuthorisationList.stream().peek(dacAuthorisation -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(dacAuthorisation.getUserNumber()))
                .findFirst()
                .ifPresentOrElse(
                        (employeeNumberAndUserId) -> dacAuthorisation.setUserId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> dacAuthorisationWithoutUserIdList.add(dacAuthorisation)
                )
        ).toList();
    }

}
