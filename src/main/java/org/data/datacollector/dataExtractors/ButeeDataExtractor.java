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
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ButeeDataExtractor {

    private final DataExtractor dataExtractor;

    public List<EmployeeNumberAndUserId> extractEmployeeNumberAndUserId(){
        return dataExtractor.extractData("user_id_employee_number" , EmployeeNumberAndUserId.class);
    }

    public List<ButeeDG> extractButeeDG(){
        return dataExtractor.extractData("DG" , ButeeDG.class);
    }

    public List<ButeeSimu> extractButeeSimu(){
        return dataExtractor.extractData("Simu" , ButeeSimu.class);
    }

    public List<ButeeSC> extractButeeSC(){
        return dataExtractor.extractData("SC" , ButeeSC.class);
    }

    public List<ButeeSS> extractButeeSS(){
        return dataExtractor.extractData("SS" , ButeeSS.class);
    }

    public List<ButeeCtrlEL> extractButeeCtrlEL(){
        return dataExtractor.extractData("CTRL EL" , ButeeCtrlEL.class);
    }

    public List<ButeeCRM> extractButeeCRM(){
        return dataExtractor.extractData("CRM" , ButeeCRM.class);
    }

    public List<ButeeELP> extractButeeELP(){
        return dataExtractor.extractData("ELP" , ButeeELP.class);
    }

}
