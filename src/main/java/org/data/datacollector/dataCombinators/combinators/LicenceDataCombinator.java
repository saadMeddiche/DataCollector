package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.Licence;
import org.data.datacollector.dataExtractors.dataHolders.dataFromIR.LicenceIR;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.extractors.LicenceDataExtractor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class LicenceDataCombinator extends DataCombinator {

    private final LicenceDataExtractor licenceDataExtractor;

    private Long START_ID = 1L;

    public List<Licence> licenceWithoutUserIdList = new ArrayList<>();

    public List<Licence> getLicenceList() {
        List<Licence> licenceList = generateLicenceList(licenceDataExtractor.extractLicenceIR());
        return attachUserIdToLicence(licenceList, licenceDataExtractor.extractEmployeeNumberAndUserId());
    }

    private List<Licence> generateLicenceList(List<LicenceIR> licenceIRList) {
        return licenceIRList.stream().map(licenceIR -> Licence.builder()
                .id(String.valueOf(START_ID++))
                .validTo(dateBuilder(licenceIR.getValidTo()))
                .examinerEmployeeNumber(licenceIR.getExaminerEmployeeNumber())
                .examinerName(licenceIR.getExaminerName())
                .build()
        ).toList();
    }

    private List<Licence> attachUserIdToLicence(List<Licence> licenceList, List<EmployeeNumberAndUserId> employeeNumberAndUserIdList) {
        return licenceList.stream().peek(licence -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(licence.getExaminerEmployeeNumber()))
                .findFirst()
                .ifPresentOrElse(
                    employeeNumberAndUserId -> licence.setUserId(employeeNumberAndUserId.getUserId()),
                    () -> licenceWithoutUserIdList.add(licence)
                )
        ).toList();
    }


}
