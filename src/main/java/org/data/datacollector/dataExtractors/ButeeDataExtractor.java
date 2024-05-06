package org.data.datacollector.dataExtractors;

import com.poiji.bind.Poiji;
import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCRM.ButeeCRM;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.ButeeCtrlEL;
import org.data.datacollector.dataExtractors.dataHolders.dataFromDG.ButeeDG;
import org.data.datacollector.dataExtractors.dataHolders.dataFromELP.ButeeELP;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSC.ButeeSC;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSS.ButeeSS;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSimu.ButeeSimu;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ButeeDataExtractor extends DataExtractor {

    public List<ButeeDG> extractButeeDG(){
        return extractData("DG" , ButeeDG.class);
    }

    public List<ButeeSimu> extractButeeSimu(){
        return extractData("Simu" , ButeeSimu.class);
    }

    public List<ButeeSC> extractButeeSC(){
        return extractData("SC" , ButeeSC.class);
    }

    public List<ButeeSS> extractButeeSS(){
        return extractData("SS" , ButeeSS.class);
    }

    public List<ButeeCtrlEL> extractButeeCtrlEL(){
        return extractData("CTRL EL" , ButeeCtrlEL.class);
    }

    public List<ButeeCRM> extractButeeCRM(){
        return extractData("CRM" , ButeeCRM.class);
    }

    public List<ButeeELP> extractButeeELP(){
        return extractData("ELP" , ButeeELP.class);
    }

}
