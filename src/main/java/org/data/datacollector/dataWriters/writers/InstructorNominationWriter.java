package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.InstructorNominationDataCombinator;
import org.data.datacollector.dataCombinators.models.InstructorNomination;
import org.data.datacollector.dataWriters.models.InstructorNominationResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstructorNominationWriter {

    private final InstructorNominationDataCombinator instructorNominationDataCombinator;

    private final Path path;

    private final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void write(){
        List<InstructorNomination> instructorNominationList = instructorNominationDataCombinator.getInstructorNominationList();

        CsvWriter.writeCsv(
                mapInstructorNominationToInstructorNominationResult(instructorNominationList),
                path.getAbsolutePathOfCsv("instructorNomination")
        );

        System.out.println("instructorNominationList Count :"+ instructorNominationList.size());
        System.out.println("instructorNominationWithoutUserId Count :"+ instructorNominationDataCombinator.instructorNominationWithoutInstructorIdList.size());
    }

    private List<InstructorNominationResult> mapInstructorNominationToInstructorNominationResult(List<InstructorNomination> instructorNominationList) {
        return instructorNominationList.stream()
                .map(instructorNomination -> modelMapper.map(instructorNomination, InstructorNominationResult.class)).toList();
    }
}
