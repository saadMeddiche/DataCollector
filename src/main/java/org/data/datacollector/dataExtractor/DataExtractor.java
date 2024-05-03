package org.data.datacollector.dataExtractor;

import com.poiji.bind.Poiji;
import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataExtractor.dataHolders.dataFromCRM.ButeeCRM;
import org.data.datacollector.dataExtractor.dataHolders.dataFromCtrlEL.ButeeCtrlEL;
import org.data.datacollector.dataExtractor.dataHolders.dataFromDG.ButeeDG;
import org.data.datacollector.dataExtractor.dataHolders.dataFromELP.ButeeELP;
import org.data.datacollector.dataExtractor.dataHolders.dataFromSC.ButeeSC;
import org.data.datacollector.dataExtractor.dataHolders.dataFromSS.ButeeSS;
import org.data.datacollector.dataExtractor.dataHolders.dataFromSimu.ButeeSimu;
import org.data.datacollector.dataExtractor.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataExtractor {

    private final Path path;

    public List<EmployeeNumberAndUserId> extractEmployeeNumberAndUserId(){
        return extractData("user_id_employee_number" , EmployeeNumberAndUserId.class);
    }

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

    private <O> List<O> extractData(String fileName , Class<O> dataHolderClass){
        return Poiji.fromExcel(new File(path.getAbsolutePathOfXlsx(fileName)), dataHolderClass);
    }

}
