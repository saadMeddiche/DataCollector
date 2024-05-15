package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.InstructorObservationCombinator;
import org.data.datacollector.dataCombinators.models.InstructorObservation;
import org.data.datacollector.dataWriters.models.InstructorObservationResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstructorObservationWriter {

    private final InstructorObservationCombinator instructorObservationCombinator;

    private final Path path;

    private final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    public void write(){

        List<InstructorObservation> instructorObservationList = instructorObservationCombinator.getInstructorObservationList();
        CsvWriter.writeCsv(
                mapInstructorObservationToInstructorObservationResult(instructorObservationList),
                path.getAbsolutePathOfCsv("instructor_observation")
        );
        System.out.println("instructorObservationList Count :"+ instructorObservationList.size());
        System.out.println("instructorObservationWithoutInstructorId Count :"+ instructorObservationCombinator.instructorObservationWithoutInstructorIdList.size());
        System.out.println("instructorObservationWithoutSieNumber Count :"+ instructorObservationCombinator.instructorObservationWithoutSieNumberList.size());
        System.out.println("instructorObservationWithoutSieId Count :"+ instructorObservationCombinator.instructorObservationWithoutSieIdList.size());
    }

    private List<InstructorObservationResult> mapInstructorObservationToInstructorObservationResult(List<InstructorObservation> instructorObservationList) {
        return instructorObservationList.stream()
                .map(instructorObservation -> modelMapper.map(instructorObservation, InstructorObservationResult.class)).toList();
    }
}
