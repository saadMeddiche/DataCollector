package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCRM.ButeeCRM;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.ButeeCtrlEL;
import org.data.datacollector.dataExtractors.dataHolders.dataFromDG.ButeeDG;
import org.data.datacollector.dataExtractors.dataHolders.dataFromELP.ButeeELP;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSC.ButeeSC;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSS.ButeeSS;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSimu.ButeeSimu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ButeeHistoryDataExtractor extends DataExtractor {

    public List<ButeeDG> extractButeeDG(){
        return extractData("data2" , ButeeDG.class);
    }

    public List<ButeeSimu> extractButeeSimu(){
        return extractData("data2" , ButeeSimu.class);
    }

    public List<ButeeSC> extractButeeSC(){
        return extractData("data2" , ButeeSC.class);
    }

    public List<ButeeSS> extractButeeSS(){
        return extractData("data2" , ButeeSS.class);
    }

    public List<ButeeCtrlEL> extractButeeCtrlEL(){
        return extractData("data2" , ButeeCtrlEL.class);
    }

    public List<ButeeCRM> extractButeeCRM(){
        return extractData("data2" , ButeeCRM.class);
    }

    public List<ButeeELP> extractButeeELP(){
        return extractData("data2" , ButeeELP.class);
    }

}
