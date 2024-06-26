package org.data.datacollector.dataExtractors.dataHolders.dataFromSS;

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
@ExcelSheet("SS")
public class ButeeSS extends ButeeData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName(" Butée SS1")
    private String validityEnd1;

    @ExcelCellName(" Butée SS2")
    private String validityEnd2;

    @ExcelCellName(" Butée SS3")
    private String validityEnd3;

    @ExcelCellName(" Butée SS4")
    private String validityEnd4;

    @Override
    public List<String> getValidityEnds(){
        return getValidityEnds(4 , ButeeSS.class);
    }
}
