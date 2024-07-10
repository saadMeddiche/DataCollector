package org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import org.data.datacollector.dataExtractors.globalDataHolders.UnknownUserData;

@Getter
@ExcelSheet("EL")
public class UserEL extends UnknownUserData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;
}
