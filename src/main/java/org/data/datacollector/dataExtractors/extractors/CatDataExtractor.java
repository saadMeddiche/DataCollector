package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCAT2.CatCAT2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatDataExtractor extends DataExtractor {


    public List<CatCAT2> extractCatCAT2(){
        return extractData("data" , CatCAT2.class);
    }
}
