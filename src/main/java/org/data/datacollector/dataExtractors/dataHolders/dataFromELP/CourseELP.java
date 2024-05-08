package org.data.datacollector.dataExtractors.dataHolders.dataFromELP;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.datacollector.dataExtractors.globalDataHolders.CourseData;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseELP extends CourseData {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("Dte Test ELP")
    private String courseDate1;

    @ExcelCellName("LIEU")
    private String place1;

    @Override
    public List<Row> getRows() {
        return getRows(1, this.getClass());
    }
}
