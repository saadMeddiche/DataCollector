package org.data.datacollector.dataExtractors.dataHolders.dataFromIR;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import org.data.datacollector.dataExtractors.globalDataHolders.UnknownUserData;

@Getter
@ExcelSheet("IR")
public class UserIR extends UnknownUserData {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Prénom")
    private String firstName;

    @ExcelCellName("Nom")
    private String lastName;
}

