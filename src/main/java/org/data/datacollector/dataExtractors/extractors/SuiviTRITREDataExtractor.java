package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.SuiviTRITRE;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuiviTRITREDataExtractor extends DataExtractor {

    public List<SuiviTRITRE> extractSuiviTRITRE(){
        return extractData("Suivi TRI_TRE" , SuiviTRITRE.class);
    }

    public List<String> extractEmployeeNumberOnlyWithSieDuAndSieAuFull(){
        return extractSuiviTRITRE().stream()
                .filter(suiviTRITRE -> suiviTRITRE.getSieDu() != null && suiviTRITRE.getSieAu() != null)
                .map(SuiviTRITRE::getEmployeeNumber)
                .toList();
    }

}
