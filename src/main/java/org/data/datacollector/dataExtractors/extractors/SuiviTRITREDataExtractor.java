package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromTRITRE.SuiviTRITRE;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuiviTRITREDataExtractor extends DataExtractor {

    public List<SuiviTRITRE> extractSuiviTRITRE(){
        return extractData("data2" , SuiviTRITRE.class);
    }

    public List<String> extractEmployeeNumberOnlyWithSieDuAndSieAuFull(){
        return extractSuiviTRITRE().stream()
                .filter(suiviTRITRE -> suiviTRITRE.getSieDu() != null && suiviTRITRE.getSieAu() != null)
                .map(SuiviTRITRE::getEmployeeNumber)
                .toList();
    }

}
