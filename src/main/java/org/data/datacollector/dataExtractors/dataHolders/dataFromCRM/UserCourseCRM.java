package org.data.datacollector.dataExtractors.dataHolders.dataFromCRM;

import com.poiji.annotation.ExcelCellName;
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
public class UserCourseCRM extends UserCourseData {
    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("date_CTL1")
    private String courseDate1;

    @ExcelCellName("Mle 1")
    private String instructorNumber1;

    @ExcelCellName("Butée 1")
    private String validityEnd1;

    @ExcelCellName("date_CTL2")
    private String courseDate2;

    @ExcelCellName("Mle 2")
    private String instructorNumber2;

    @ExcelCellName("Butée 2")
    private String validityEnd2;

    @ExcelCellName("date_CTL3")
    private String courseDate3;

    @ExcelCellName("Mle 3")
    private String instructorNumber3;

    @ExcelCellName("Butée 3")
    private String validityEnd3;

    @ExcelCellName("date_CTL4")
    private String courseDate4;

    @ExcelCellName("Mle 4")
    private String instructorNumber4;

    @ExcelCellName("Butée 4")
    private String validityEnd4;

    @Override
    public List<Row> getRows() {
        return getRows(4, this.getClass());
    }

}
