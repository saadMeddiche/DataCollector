package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.InstructorFlightPlaceDataDGCombinator;
import org.data.datacollector.dataCombinators.models.InstructorFlightPlaceDg;
import org.data.datacollector.dataWriters.models.InstructorFlightPlaceDgResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstructorFlightPlaceDataDGWriter {

    private final InstructorFlightPlaceDataDGCombinator instructorFlightPlaceDataDGCombinator;

    private final Path path;
    private final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void write(){

        List<InstructorFlightPlaceDg> instructorFlightPlaceDgList = instructorFlightPlaceDataDGCombinator.getInstructorFlightPlaceDgList();

        CsvWriter.writeCsv(
                mapInstructorFlightPlaceDataDGToInstructorFlightPlaceDataDGResult(instructorFlightPlaceDgList),
                path.getAbsolutePathOfCsv("instructorFlightPlaceDataDG")
        );

        System.out.println("instructorFlightPlaceDgList Count :"+ instructorFlightPlaceDgList.size());
        System.out.println("instructorFlightPlaceDgWithoutInstructorIdList Count :"+ instructorFlightPlaceDataDGCombinator.instructorFlightPlaceDgWithoutInstructorIdList.size());
        System.out.println("instructorFlightPlaceDgWithoutSieNumberList Count :"+ instructorFlightPlaceDataDGCombinator.instructorFlightPlaceDgWithoutSieNumberList.size());
        System.out.println("instructorFlightPlaceDgWithoutSieIdList Count :"+ instructorFlightPlaceDataDGCombinator.instructorFlightPlaceDgWithoutSieIdList.size());
    }

    private List<InstructorFlightPlaceDgResult> mapInstructorFlightPlaceDataDGToInstructorFlightPlaceDataDGResult(List<InstructorFlightPlaceDg> instructorFlightPlaceDgList) {
        return instructorFlightPlaceDgList.stream()
                .map(instructorFlightPlaceDg -> modelMapper.map(instructorFlightPlaceDg, InstructorFlightPlaceDgResult.class)).toList();
    }
}
