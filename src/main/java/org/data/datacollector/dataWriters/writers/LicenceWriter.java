package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.LicenceDataCombinator;
import org.data.datacollector.dataCombinators.models.Licence;
import org.data.datacollector.dataWriters.models.LicenceResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LicenceWriter {

    private final LicenceDataCombinator licenceDataCombinator;

    private final Path path;

    public void write(){

        List<Licence> licenceList = licenceDataCombinator.getLicenceList();

        CsvWriter.writeCsv(
                mapLicenceToLicenceResult(licenceList),
                path.getAbsolutePathOfCsv("licence")
        );

        System.out.println("licenceList Count :"+ licenceList.size());
        System.out.println("licenceWithoutUserId Count :"+ licenceDataCombinator.licenceWithoutUserIdList.size());

//        licenceDataCombinator.licenceWithoutUserIdList.forEach(
//                licence -> System.out.println(licence.toString())
//        );
    }

    public List<LicenceResult> mapLicenceToLicenceResult(List<Licence> licenceList) {
        return licenceList.stream()
                .map(licence -> LicenceResult.builder()
                        .id(licence.getId())
                        .validTo(licence.getValidTo())
                        .examinerEmployeeNumber(licence.getExaminerEmployeeNumber())
                        .examinerName(licence.getExaminerName())
                        .userId(licence.getUserId())
                        .build())
                .toList();
    }
}
