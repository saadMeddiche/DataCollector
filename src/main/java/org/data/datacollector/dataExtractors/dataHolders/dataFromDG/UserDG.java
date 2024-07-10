package org.data.datacollector.dataExtractors.dataHolders.dataFromDG;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;

@Getter
@ExcelSheet("DG")
public class UserDG {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;
}
