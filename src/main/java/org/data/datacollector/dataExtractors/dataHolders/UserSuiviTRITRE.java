package org.data.datacollector.dataExtractors.dataHolders;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;

@Getter
@ExcelSheet("Suivi TRI_TRE")
public class UserSuiviTRITRE {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Prénom")
    private String firstName;

    @ExcelCellName("Nom")
    private String lastName;
}
