package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.DacAuthorisationDataCombinator;
import org.data.datacollector.dataCombinators.models.DacAuthorisation;
import org.data.datacollector.dataWriters.models.DacAuthorisationResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DacAuthorisationWriter {

    private final DacAuthorisationDataCombinator dacAuthorisationDataCombinator;
    private final Path path;

    private final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void write(){
        List<DacAuthorisation> dacAuthorisationList = dacAuthorisationDataCombinator.getDacAuthorisationList();

        CsvWriter.writeCsv(
                mapDacAuthorisationToDacAuthorisationResult(dacAuthorisationList),
                path.getAbsolutePathOfCsv("dac_autorisation")
        );

        System.out.println("dacAuthorisationList Count :"+ dacAuthorisationList.size());
        System.out.println("dacAuthorisationWithoutUserId Count :"+ dacAuthorisationDataCombinator.dacAuthorisationWithoutUserIdList.size());
    }

    private List<DacAuthorisationResult> mapDacAuthorisationToDacAuthorisationResult(List<DacAuthorisation> dacAuthorisationList) {
        return dacAuthorisationList.stream()
                .map(dacAuthorisation -> modelMapper.map(dacAuthorisation, DacAuthorisationResult.class)).toList();
    }
}
