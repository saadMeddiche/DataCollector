package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.LineCheckDataCombinator;
import org.data.datacollector.dataCombinators.models.LineCheck;
import org.data.datacollector.dataWriters.models.LineCheckResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineCheckWriter {

    private final LineCheckDataCombinator lineCheckDataCombinator;

    private final Path path;

    private final ModelMapper mapper = new ModelMapper();

    {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void write(){
        List<LineCheck> lineCheckList = lineCheckDataCombinator.getLineCheckList();

        CsvWriter.writeCsv(
                mapLineCheckToLineCheckResult(lineCheckList),
                path.getAbsolutePathOfCsv("line_check")
        );

        System.out.println("lineCheckList Count :"+ lineCheckList.size());
        System.out.println("lineCheckWithoutInstructorId Count :"+ lineCheckDataCombinator.lineCheckWithoutInstructorIdList.size());
        lineCheckDataCombinator.lineCheckWithoutInstructorIdList.forEach(
                lineCheck -> System.out.println(
                    "|N¤ I:" + lineCheck.getInstructorNumber()
                    + "|Date:" + lineCheck.getDateOfOrigin()
                    + "|ID¤ i:" + lineCheck.getInstructorId()
                )
        );
    }

    private List<LineCheckResult> mapLineCheckToLineCheckResult(List<LineCheck> lineCheckList) {
        return lineCheckList.stream()
                .map(lineCheck ->  mapper.map(lineCheck, LineCheckResult.class)).toList();
    }
}
