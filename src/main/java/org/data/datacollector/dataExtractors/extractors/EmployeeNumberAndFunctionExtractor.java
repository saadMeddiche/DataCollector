package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.EmployeeNumberAndFunction;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCAT2.CatCAT2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeNumberAndFunctionExtractor extends DataExtractor {


    public List<EmployeeNumberAndFunction> extract(){
        return extractData("new_functions_user_data" , EmployeeNumberAndFunction.class);
    }
}
