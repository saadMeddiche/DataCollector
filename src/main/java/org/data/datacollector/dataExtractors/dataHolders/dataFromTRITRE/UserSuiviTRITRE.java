package org.data.datacollector.dataExtractors.dataHolders.dataFromTRITRE;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import org.data.datacollector.dataExtractors.globalDataHolders.UnknownUserData;

@Getter
@ExcelSheet("Suivi TRI_TRE")
public class UserSuiviTRITRE extends UnknownUserData {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Prénom")
    private String firstName;

    @ExcelCellName("Nom")
    private String lastName;
}
