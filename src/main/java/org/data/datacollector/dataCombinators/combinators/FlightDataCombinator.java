package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.Flight;
import org.data.datacollector.dataCombinators.models.LineCheck;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.CtrlEL;
import org.data.datacollector.dataExtractors.extractors.CtrlELDataExtractor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class FlightDataCombinator  extends DataCombinator {

    private final CtrlELDataExtractor ctrlELDataExtractor;

    private final LineCheckDataCombinator lineCheckDataCombinator;

    private Long START_ID = 1L;

    public List<Flight> flightWithoutLineCheckIdList =  new ArrayList<>();

    public List<Flight> getFlightList(){
        List<Flight> flightList = generateFlight(ctrlELDataExtractor.extractCtrlEL());
        return attachLineCheckIdToFlight(flightList , lineCheckDataCombinator.getLineCheckList());
    }

    private List<Flight> generateFlight(List<CtrlEL> ctrlELList){
        return ctrlELList.stream().flatMap(
                ctrlEL -> ctrlEL.getRows().stream()
                .filter(row -> row.getStartDate() != null && row.getInstructorNumber() != null)
                .map(row -> Flight.builder()
                        .id(String.valueOf(START_ID++))
                        .dateOfOrigin(dateBuilder(row.getStartDate()))
                        .instructorNumber(row.getInstructorNumber())
                    .build()
                )).toList();
    }

    private List<Flight> attachLineCheckIdToFlight(List<Flight> flightList , List<LineCheck> lineCheckList){
       return flightList.stream().peek(flight -> lineCheckList.stream()
               .filter(lineCheck ->
                       lineCheck.getInstructorNumber().equals(flight.getInstructorNumber())
                       && lineCheck.getDateOfOrigin().equals(flight.getDateOfOrigin())
               )
               .findFirst()
               .ifPresentOrElse(
                       lineCheck -> flight.setLineCheckId(lineCheck.getId())
                       ,
                       () -> flightWithoutLineCheckIdList.add(flight)
               )
       ).toList();
    }
}
