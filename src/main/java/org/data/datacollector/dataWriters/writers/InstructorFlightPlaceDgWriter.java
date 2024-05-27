package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.InstructorFlightPlaceDgCombinator;
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
public class InstructorFlightPlaceDgWriter {

    private final InstructorFlightPlaceDgCombinator instructorFlightPlaceDgCombinator;

    private final Path path;
    private final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void write(){

        List<InstructorFlightPlaceDg> instructorFlightPlaceDgList = instructorFlightPlaceDgCombinator.getInstructorFlightPlaceDgList();

        CsvWriter.writeCsv(
                mapInstructorFlightPlaceDataDGToInstructorFlightPlaceDataDGResult(instructorFlightPlaceDgList),
                path.getAbsolutePathOfCsv("instructor_flight_place_dg")
        );

        System.out.println("instructorFlightPlaceDgList Count :"+ instructorFlightPlaceDgList.size());
        System.out.println("instructorFlightPlaceDgWithoutInstructorIdList Count :"+ instructorFlightPlaceDgCombinator.instructorFlightPlaceDgWithoutInstructorIdList.size());
        System.out.println("instructorFlightPlaceDgWithoutSieNumberList Count :"+ instructorFlightPlaceDgCombinator.instructorFlightPlaceDgWithoutSieNumberList.size());
        System.out.println("instructorFlightPlaceDgWithoutSieIdList Count :"+ instructorFlightPlaceDgCombinator.instructorFlightPlaceDgWithoutSieIdList.size());

//        instructorFlightPlaceDgCombinator.instructorFlightPlaceDgWithoutSieNumberList.forEach(
//                i -> System.out.println(i.toString())
//        );

    }

    private List<InstructorFlightPlaceDgResult> mapInstructorFlightPlaceDataDGToInstructorFlightPlaceDataDGResult(List<InstructorFlightPlaceDg> instructorFlightPlaceDgList) {
        return instructorFlightPlaceDgList.stream()
                .map(instructorFlightPlaceDg -> modelMapper.map(instructorFlightPlaceDg, InstructorFlightPlaceDgResult.class)).toList();
    }
}
