package org.data.datacollector.dataExtractors.dataHolders.dataFromDG;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.datacollector.dataExtractors.global.ButeeData;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButeeDG extends ButeeData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("Butée DG1")
    private String validityEnd1;

    @ExcelCellName("Butée DG2")
    private String validityEnd2;

    @ExcelCellName("Butée DG3")
    private String validityEnd3;

    @ExcelCellName("Butée DG4")
    private String validityEnd4;

    @Override
    public List<Date> getValidityEnds(){
        return getValidityEnds(4 , ButeeDG.class);
    }
}
