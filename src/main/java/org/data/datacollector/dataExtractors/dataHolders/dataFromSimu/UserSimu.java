package org.data.datacollector.dataExtractors.dataHolders.dataFromSimu;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.*;

@Getter
@ExcelSheet("Simu")
public class UserSimu {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;
}
