package org.data.datacollector.dataExtractors.dataHolders.dataFromSC;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import org.data.datacollector.dataExtractors.globalDataHolders.UnknownUserData;

@Getter
@ExcelSheet("SC")
public class UserSC extends UnknownUserData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;
}
