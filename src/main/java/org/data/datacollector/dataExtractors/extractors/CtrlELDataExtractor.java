package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.CtrlEL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CtrlELDataExtractor extends DataExtractor {

    public List<CtrlEL> extractCtrlEL(){
        return extractData("CTRL EL" , CtrlEL.class);
    }

}
