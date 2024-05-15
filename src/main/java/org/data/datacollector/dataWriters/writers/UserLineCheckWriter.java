package org.data.datacollector.dataWriters.writers;


import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.UserLineCheckDataCombinator;
import org.data.datacollector.dataCombinators.models.UserLineCheck;
import org.data.datacollector.dataWriters.models.UserLineCheckResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserLineCheckWriter {

    private final UserLineCheckDataCombinator userLineCheckDataCombinator;

    private final Path path;

    private final ModelMapper mapper = new ModelMapper();

    {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    public void write() {

        List<UserLineCheck> userLineCheckList = userLineCheckDataCombinator.getUserLineCheckList();
        CsvWriter.writeCsv(
                mapUserLineCheckToUserLineCheckResult(userLineCheckList),
                path.getAbsolutePathOfCsv("user_line_check")
        );

        System.out.println("userLineCheckList Count :" + userLineCheckList.size());
        System.out.println("userLineCheckWithoutLineCheckId Count :" + userLineCheckDataCombinator.userLineCheckWithoutLineCheckIdList.size());
        System.out.println("userLineCheckWithoutTraineeId Count :" + userLineCheckDataCombinator.userLineCheckWithoutTraineeIdList.size());
    }

    private List<UserLineCheckResult> mapUserLineCheckToUserLineCheckResult(List<UserLineCheck> userLineCheckList) {
        return userLineCheckList.stream()
                .map(userLineCheck -> mapper.map(userLineCheck, UserLineCheckResult.class)).toList();
    }
}