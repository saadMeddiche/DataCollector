package org.data.datacollector.dataExtractors.dataHolders.dataFromSC;

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
public class ButeeSC extends ButeeData {

    @ExcelCellName("MATR")
    private Long employeeNumber;

    @ExcelCellName("Butée Sc1")
    private String validityEnd1;

    @ExcelCellName("Butée Sc2")
    private String validityEnd2;

    @ExcelCellName("Butée Sc3")
    private String validityEnd3;

    @ExcelCellName("Butée Sc4")
    private String validityEnd4;

    @Override
    public List<Date> getValidityEnds() {
        return getValidityEnds(4 , ButeeSC.class);
    }

}
