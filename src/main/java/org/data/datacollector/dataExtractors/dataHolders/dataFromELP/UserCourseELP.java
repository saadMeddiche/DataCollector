package org.data.datacollector.dataExtractors.dataHolders.dataFromELP;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.datacollector.dataExtractors.globalDataHolders.UserCourseData;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet("ELP")
public class UserCourseELP extends UserCourseData {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Dte Test ELP")
    private String courseDate1;

    @ExcelCellName("Butée")
    private String validityEnd1;

    @Override
    public List<Row> getRows() {
        return getRows(1, this.getClass());
    }
}
