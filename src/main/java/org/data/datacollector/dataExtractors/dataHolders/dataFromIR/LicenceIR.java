package org.data.datacollector.dataExtractors.dataHolders.dataFromIR;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenceIR {

    @ExcelCellName("Mle")
    private String userNumber;

    @ExcelCellName("BUTEE")
    private String validTo;

    @ExcelCellName("Mle_Examiner")
    private String examinerEmployeeNumber;

    @ExcelCellName("IR_Examiner")
    private String examinerName;

}
