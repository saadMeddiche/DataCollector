package org.data.datacollector.dataExtractors.dataHolders.dataFromSuiviTRITRE;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuiviTRITRE {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("nominationStatus")
    private String nominationStatus;

    @ExcelCellName("nominationDate")
    private String nominationDate;

    @ExcelCellName("sieDu")
    private String sieDu;

    @ExcelCellName("sieAu")
    private String sieAu;
}
