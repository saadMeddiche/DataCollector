package org.data.datacollector.dataExtractors.dataHolders.dataFromDG;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import org.data.datacollector.dataExtractors.globalDataHolders.UnknownUserData;

@Getter
@ExcelSheet("DG")
public class UserDG extends UnknownUserData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;
}
