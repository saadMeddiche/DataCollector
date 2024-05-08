package org.data.datacollector.dataExtractors.dataHolders.dataFromCRM;

import com.poiji.annotation.ExcelCellName;
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
public class ButeeCRM extends ButeeData {

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

    @Override
    public List<String> getValidityEnds(){
        return getValidityEnds(4 , ButeeCRM.class);
    }
}
