package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.ButeeHistoryDataCombinator;
import org.data.datacollector.dataCombinators.models.ButeeHistory;
import org.data.datacollector.dataWriters.models.ButeeHistoryResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ButeeHistoryWriter {

    private final ButeeHistoryDataCombinator buteeHistoryDataCombinator;

    private final Path path;

    private final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    public void write(){
        List<ButeeHistory> buteeHistoryList = buteeHistoryDataCombinator.getButeeHistoryList();

        CsvWriter.writeCsv(
                mapButeeToButeeResult(buteeHistoryList),
                path.getAbsolutePathOfCsv("butee_history")
        );

        System.out.println("buteeHistoryList Count :"+ buteeHistoryList.size());
        System.out.println("buteeHistoryWithoutUserId Count :"+ buteeHistoryDataCombinator.buteeHistoryWithoutUserIdList.size());
    }

    private List<ButeeHistoryResult> mapButeeToButeeResult(List<ButeeHistory> buteeHistoryList) {
        return buteeHistoryList.stream()
                .map(buteeHistory -> modelMapper.map(buteeHistory, ButeeHistoryResult.class)).toList();
    }
}
