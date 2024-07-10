package org.data.datacollector.dataExtractors.dataHolders.dataFromSS;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;

@Getter
@ExcelSheet("SS")
public class UserSS {
    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;
}
