package org.data.datacollector.dataExtractors.dataHolders.dataFromIR;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;

@Getter
@ExcelSheet("IR")
public class UserIR {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Prénom")
    private String firstName;

    @ExcelCellName("Nom")
    private String lastName;
}

