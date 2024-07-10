package org.data.datacollector.dataExtractors.dataHolders.dataFromELP;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;

@Getter
@ExcelSheet("ELP")
public class UserELP {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Prénom")
    private String firstName;

    @ExcelCellName("Nom")
    private String lastName;
}
