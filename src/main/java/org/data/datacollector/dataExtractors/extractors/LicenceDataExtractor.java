package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromIR.LicenceIR;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LicenceDataExtractor extends DataExtractor {

    public List<LicenceIR> extractLicenceIR() {
        return extractData("data", LicenceIR.class);
    }
}
