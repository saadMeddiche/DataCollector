package org.data.datacollector.dataExtractors.dataHolders.dataFromSimu;

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
@ExcelSheet("Simu")
public class ButeeSimu extends ButeeData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("Butée 1")
    private String validityEnd1;

    @ExcelCellName("Butée 2")
    private String validityEnd2;

    @ExcelCellName("Butée 3")
    private String validityEnd3;

    @ExcelCellName("Butée 4")
    private String validityEnd4;

    @ExcelCellName("Butée 5")
    private String validityEnd5;

    @ExcelCellName("Butée 6")
    private String validityEnd6;

    @ExcelCellName("Butée 7")
    private String validityEnd7;

    @ExcelCellName("Butée 8")
    private String validityEnd8;

    @Override
    public List<String> getValidityEnds(){
        return getValidityEnds(8 , ButeeSimu.class);
    }
}
