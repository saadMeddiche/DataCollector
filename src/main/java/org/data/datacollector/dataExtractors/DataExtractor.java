package org.data.datacollector.dataExtractors;

import com.poiji.bind.Poiji;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.services.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class DataExtractor {

    @Autowired
    private Path path;

    public List<EmployeeNumberAndUserId> extractEmployeeNumberAndUserId(){
        return extractData("user_id_employee_number" , EmployeeNumberAndUserId.class);
    }

    public  <O> List<O> extractData(String fileName , Class<O> dataHolderClass){
        return Poiji.fromExcel(new File(path.getAbsolutePathOfXlsx(fileName)), dataHolderClass);
    }
}
