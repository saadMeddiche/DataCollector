package org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL;

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
public class ButeeCtrlEL extends ButeeData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("Butée EL1")
    private String validityEnd1;

    @ExcelCellName("Butée EL2")
    private String validityEnd2;

    @ExcelCellName("Butée EL3")
    private String validityEnd3;

    @ExcelCellName("Butée EL4")
    private String validityEnd4;

    @Override
    public List<Date> getValidityEnds(){
        return getValidityEnds(4 , ButeeCtrlEL.class);
    }
}
