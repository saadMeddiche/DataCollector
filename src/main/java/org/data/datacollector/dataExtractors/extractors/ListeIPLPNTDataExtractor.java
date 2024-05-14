package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.ListeIPLPNT;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListeIPLPNTDataExtractor extends DataExtractor {

    public List<ListeIPLPNT> extractListeIPLPNT() {
        return extractData("LISTE IPL PNT", ListeIPLPNT.class);
    }
}
