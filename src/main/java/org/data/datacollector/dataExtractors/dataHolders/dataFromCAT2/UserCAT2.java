package org.data.datacollector.dataExtractors.dataHolders.dataFromCAT2;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;

@Getter
@ExcelSheet("CAT 2,3")
public class UserCAT2 {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;
}
