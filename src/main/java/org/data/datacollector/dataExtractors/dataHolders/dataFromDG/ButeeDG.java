package org.data.datacollector.dataExtractors.dataHolders.dataFromDG;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.datacollector.dataExtractors.globalDataHolders.ButeeData;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet("DG")
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
    public List<String> getValidityEnds(){
        return getValidityEnds(4 , ButeeDG.class);
    }
}
