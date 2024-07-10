package org.data.datacollector.dataExtractors.dataHolders.dataFromCRM;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;

@Getter
@ExcelSheet("CRM")
public class UserCRM {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;
}
