package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.ButeeDataCombinator;
import org.data.datacollector.dataCombinators.models.Butee;
import org.data.datacollector.dataWriters.models.ButeeResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ButeeWriter {

    private final ButeeDataCombinator buteeDataCombinator;

    private final Path path;

    public void write(){
        List<Butee> buteeList = buteeDataCombinator.getButeeList();

        CsvWriter.writeCsv(
                mapButeeToButeeResult(buteeList),
                path.getAbsolutePathOfCsv("butee")
        );

        System.out.println("buteeList Count :"+ buteeList.size());
        System.out.println("buteeWithoutUserId Count :"+ buteeDataCombinator.buteeWithoutUserIdList.size());
    }

    private List<ButeeResult> mapButeeToButeeResult(List<Butee> buteeList) {
        return buteeList.stream()
                .map(butee -> ButeeResult.builder()
                        .id(butee.getId())
                        .jeppesenCode(butee.getJeppesenCode())
                        .validityEnd(butee.getValidityEnd())
                        .userId(butee.getUserId())
                        .build()
                ).toList();
    }
}
