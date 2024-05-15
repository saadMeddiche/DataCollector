package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.InstructorSimuPlaceDroiteCombinator;
import org.data.datacollector.dataCombinators.models.InstructorSimuPlaceDroite;
import org.data.datacollector.dataWriters.models.InstructorSimuPlaceDroiteResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstructorSimuPlaceDroiteWriter {

    private final InstructorSimuPlaceDroiteCombinator instructorSimuPlaceDroiteCombinator;

    private final Path path;
    private final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void write(){

        List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteList = instructorSimuPlaceDroiteCombinator.getInstructorSimuPlaceDroiteList();

        CsvWriter.writeCsv(
                mapInstructorFlightPlaceDataDGToInstructorFlightPlaceDataDGResult(instructorSimuPlaceDroiteList),
                path.getAbsolutePathOfCsv("instructor_simu_place_droite")
        );

        System.out.println("instructorSimuPlaceDroiteList Count :"+ instructorSimuPlaceDroiteList.size());
        System.out.println("instructorSimuPlaceDroiteWithoutInstructorIdList Count :"+ instructorSimuPlaceDroiteCombinator.instructorSimuPlaceDroiteWithoutInstructorIdList.size());
        System.out.println("instructorSimuPlaceDroiteWithoutSieNumberList Count :"+ instructorSimuPlaceDroiteCombinator.instructorSimuPlaceDroiteWithoutSieNumberList.size());
        System.out.println("instructorSimuPlaceDroiteWithoutSieIdList Count :"+ instructorSimuPlaceDroiteCombinator.instructorSimuPlaceDroiteWithoutSieIdList.size());
    }

    private List<InstructorSimuPlaceDroiteResult> mapInstructorFlightPlaceDataDGToInstructorFlightPlaceDataDGResult(List<InstructorSimuPlaceDroite> instructorSimuPlaceDroiteList) {
        return instructorSimuPlaceDroiteList.stream()
                .map(instructorSimuPlaceDroite -> modelMapper.map(instructorSimuPlaceDroite, InstructorSimuPlaceDroiteResult.class)).toList();
    }
}
