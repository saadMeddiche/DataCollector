package org.data.datacollector.dataExtractors.dataHolders.dataFromELP;

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
public class ButeeELP extends ButeeData {
    @ExcelCellName("Mle")
    private Long employeeNumber;

    @ExcelCellName("Butée")
    private String validityEnd1;

    @Override
    public List<Date> getValidityEnds(){
        return getValidityEnds(1 , ButeeELP.class);
    }
}
