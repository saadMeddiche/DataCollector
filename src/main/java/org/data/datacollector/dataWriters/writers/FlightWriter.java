package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.FlightDataCombinator;
import org.data.datacollector.dataCombinators.models.Flight;
import org.data.datacollector.dataWriters.models.FlightResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FlightWriter {

    private final FlightDataCombinator flightDataCombinator;

    private final Path path;

    private final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void write(){
        List<Flight> flightList = flightDataCombinator.getFlightList();

        CsvWriter.writeCsv(
                mapFlightToFlightResult(flightList),
                path.getAbsolutePathOfCsv("flight")
        );

        System.out.println("flightList Count :"+ flightList.size());
        System.out.println("flightWithoutLineCheckId Count :"+ flightDataCombinator.flightWithoutLineCheckIdList.size());

        flightDataCombinator.flightWithoutLineCheckIdList.forEach(
                flight -> System.out.println(flight.getDateOfOrigin() + " " + flight.getInstructorNumber() + " " + flight.getId() + " " + flight.getLineCheckId())
        );

    }

    private List<FlightResult> mapFlightToFlightResult(List<Flight> flightList) {
        return flightList.stream()
                .map(flight -> modelMapper.map(flight, FlightResult.class)).toList();
    }
}
