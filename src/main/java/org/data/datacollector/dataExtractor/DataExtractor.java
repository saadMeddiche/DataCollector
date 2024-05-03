package org.data.datacollector.dataExtractor;

import com.poiji.bind.Poiji;
import org.data.datacollector.dataExtractor.dataHolders.dataFromSC.ButeeSC;
import org.data.datacollector.dataExtractor.dataHolders.dataFromSimu.ButeeSimu;
import org.data.datacollector.dataExtractor.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.services.Path;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class DataExtractor {

    private final ResourceLoader resourceLoader;

    private final Path path;
    public List<ButeeSimu> buteeSimuList;
    public List<EmployeeNumberAndUserId> employeeNumberAndUserIdList;
    public List<ButeeSC> buteeSCList;

    DataExtractor(ResourceLoader resourceLoader, Path path){
        this.resourceLoader = resourceLoader;
        this.path = path;
        this.buteeSimuList = extractButeeSimu();
        this.employeeNumberAndUserIdList = extractEmployeeNumberAndUserId();
        this.buteeSCList = extractButeeSC();
    }

    private List<EmployeeNumberAndUserId> extractEmployeeNumberAndUserId(){
        return extractData("user_id_employee_number" , EmployeeNumberAndUserId.class);
    }

    private List<ButeeSimu> extractButeeSimu(){
        return extractData("Simu" , ButeeSimu.class);
    }

    private List<ButeeSC> extractButeeSC(){
        return extractData("SC" , ButeeSC.class);
    }

    private <O> List<O> extractData(String fileName , Class<O> dataHolderClass){
        return Poiji.fromExcel(new File(path.getAbsolutePathOfXlsx(fileName)), dataHolderClass);
    }


}
