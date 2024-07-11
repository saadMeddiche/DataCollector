package org.data.datacollector.dataExtractors.dataHolders.dataFromCRM;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import org.data.datacollector.dataExtractors.globalDataHolders.UnknownUserData;

@Getter
@ExcelSheet("CRM")
public class UserCRM extends UnknownUserData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("FONCTION") // Problem with excel sheet
    private String firstName;

    @ExcelCellName("NOM")  // Problem with excel sheet
    private String lastName;
}
