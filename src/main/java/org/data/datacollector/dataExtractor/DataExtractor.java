package org.data.datacollector.dataExtractor;

import com.poiji.bind.Poiji;
import org.data.datacollector.dataExtractor.dataHolders.dataFromCRM.ButeeCRM;
import org.data.datacollector.dataExtractor.dataHolders.dataFromCtrlEL.ButeeCtrlEL;
import org.data.datacollector.dataExtractor.dataHolders.dataFromDG.ButeeDG;
import org.data.datacollector.dataExtractor.dataHolders.dataFromSC.ButeeSC;
import org.data.datacollector.dataExtractor.dataHolders.dataFromSS.ButeeSS;
import org.data.datacollector.dataExtractor.dataHolders.dataFromSimu.ButeeSimu;
import org.data.datacollector.dataExtractor.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class DataExtractor {

    private final Path path;
    public List<EmployeeNumberAndUserId> employeeNumberAndUserIdList;
    public List<ButeeSimu> buteeSimuList;
    public List<ButeeSC> buteeSCList;
    public List<ButeeDG> buteeDGList;
    public List<ButeeSS> buteeSSList;
    public List<ButeeCtrlEL> buteeCtrlELList;
    public List<ButeeCRM> buteeCRMList;

    DataExtractor(Path path){
        this.path = path;
        this.employeeNumberAndUserIdList = extractEmployeeNumberAndUserId();
        this.buteeSimuList = extractButeeSimu();
        this.buteeSCList = extractButeeSC();
        this.buteeDGList = extractButeeDG();
        this.buteeSSList = extractButeeSS();
        this.buteeCtrlELList = extractButeeCtrlEL();
        this.buteeCRMList = extractButeeCRM();
    }

    private List<EmployeeNumberAndUserId> extractEmployeeNumberAndUserId(){
        return extractData("user_id_employee_number" , EmployeeNumberAndUserId.class);
    }

    private List<ButeeDG> extractButeeDG(){
        return extractData("DG" , ButeeDG.class);
    }

    private List<ButeeSimu> extractButeeSimu(){
        return extractData("Simu" , ButeeSimu.class);
    }

    private List<ButeeSC> extractButeeSC(){
        return extractData("SC" , ButeeSC.class);
    }

    private List<ButeeSS> extractButeeSS(){
        return extractData("SS" , ButeeSS.class);
    }

    private List<ButeeCtrlEL> extractButeeCtrlEL(){
        return extractData("CTRL EL" , ButeeCtrlEL.class);
    }

    private List<ButeeCRM> extractButeeCRM(){
        return extractData("CRM" , ButeeCRM.class);
    }

    private <O> List<O> extractData(String fileName , Class<O> dataHolderClass){
        return Poiji.fromExcel(new File(path.getAbsolutePathOfXlsx(fileName)), dataHolderClass);
    }

}
