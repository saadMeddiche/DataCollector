package org.data.datacollector.dataExtractors.dataHolders;

import com.poiji.annotation.ExcelCellName;
import lombok.Getter;

@Getter
public class EmployeeNumberAndFunction {

    @ExcelCellName("employee_number")
    private String employeeNumber;

    @ExcelCellName("cFunction")
    private String cFunction;
}
