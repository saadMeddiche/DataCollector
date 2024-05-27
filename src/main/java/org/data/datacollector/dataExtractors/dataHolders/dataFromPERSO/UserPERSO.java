package org.data.datacollector.dataExtractors.dataHolders.dataFromPERSO;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet("PERSO")
public class UserPERSO {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("PRENOM")
    private String firstName;

    @ExcelCellName("NOM")
    private String lastName;

    @ExcelCellName("FONCTION")
    private String cFunction;

    @ExcelCellName("MACHINE")
    private String airplaneSpecialty;

    @ExcelCellName("Dte_LACHER")
    private String releaseDate;

    @ExcelCellName("Mle")
    private String releasedByEmployeeNumber;

}
