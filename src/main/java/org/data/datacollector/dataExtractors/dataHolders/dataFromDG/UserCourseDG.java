package org.data.datacollector.dataExtractors.dataHolders.dataFromDG;

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
@ExcelSheet("DG")
public class UserCourseDG extends UserCourseData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("Dte DG1")
    private String courseDate1;

    @ExcelCellName("Mle1")
    private String instructorNumber1;

    @ExcelCellName("Butée DG1")
    private String validityEnd1;

    @ExcelCellName("Dte DG2")
    private String courseDate2;

    @ExcelCellName("Mle2")
    private String instructorNumber2;

    @ExcelCellName("Butée DG2")
    private String validityEnd2;

    @ExcelCellName("Dte DG3")
    private String courseDate3;

    @ExcelCellName("Mle3")
    private String instructorNumber3;

    @ExcelCellName("Butée DG3")
    private String validityEnd3;

    @ExcelCellName("Dte DG4")
    private String courseDate4;

    @ExcelCellName("Mle4")
    private String instructorNumber4;

    @ExcelCellName("Butée DG4")
    private String validityEnd4;

    @Override
    public List<Row> getRows() {
        return getRows(4, this.getClass());
    }
}
