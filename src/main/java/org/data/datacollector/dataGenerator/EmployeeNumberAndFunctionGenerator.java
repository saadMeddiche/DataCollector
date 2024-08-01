package org.data.datacollector.dataGenerator;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataExtractors.extractors.EmployeeNumberAndFunctionExtractor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeNumberAndFunctionGenerator {

    private final EmployeeNumberAndFunctionExtractor employeeNumberAndFunctionExtractor;

    public void generateChangeSet(){

        String updateTag = "<update tableName=\"user\">\n" +
                "            <column name=\"cFunction\" value=\"%s\"/>\n" +
                "            <where>employeeNumber = '%s'</where>\n" +
                "        </update>";

        employeeNumberAndFunctionExtractor.extract().forEach(employeeNumberAndFunction -> {
            System.out.println(String.format(updateTag, employeeNumberAndFunction.getCFunction(), employeeNumberAndFunction.getEmployeeNumber()));
        });
    }
}
