package org.data.datacollector.dataExtractors.dataHolders.dataFromELP;

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
@ExcelSheet("ELP")
public class ButeeELP extends ButeeData {
    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Butée")
    private String validityEnd1;

    @Override
    public List<String> getValidityEnds(){
        return getValidityEnds(1 , ButeeELP.class);
    }
}
