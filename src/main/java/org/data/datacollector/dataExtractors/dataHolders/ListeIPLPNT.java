package org.data.datacollector.dataExtractors.dataHolders;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListeIPLPNT {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Instructeur")
    private String employeeName;
}
