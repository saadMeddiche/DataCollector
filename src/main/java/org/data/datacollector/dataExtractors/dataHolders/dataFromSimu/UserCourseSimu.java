package org.data.datacollector.dataExtractors.dataHolders.dataFromSimu;

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
@ExcelSheet("Simu")
public class UserCourseSimu extends UserCourseData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("date1")
    private String courseDate1;

    @ExcelCellName("Mle1")
    private String instructorNumber1;

    @ExcelCellName("Butée 1")
    private String validityEnd1;

    @ExcelCellName("date2")
    private String courseDate2;

    @ExcelCellName("Mle2")
    private String instructorNumber2;

    @ExcelCellName("Butée 2")
    private String validityEnd2;

    @ExcelCellName("date3")
    private String courseDate3;

    @ExcelCellName("Mle3")
    private String instructorNumber3;

    @ExcelCellName("Butée 3")
    private String validityEnd3;

    @ExcelCellName("date4")
    private String courseDate4;

    @ExcelCellName("Mle4")
    private String instructorNumber4;

    @ExcelCellName("Butée 4")
    private String validityEnd4;

    @ExcelCellName("date5")
    private String courseDate5;

    @ExcelCellName("Mle5")
    private String instructorNumber5;

    @ExcelCellName("Butée 5")
    private String validityEnd5;

    @ExcelCellName("date6")
    private String courseDate6;

    @ExcelCellName("Mle6")
    private String instructorNumber6;

    @ExcelCellName("Butée 6")
    private String validityEnd6;

    @ExcelCellName("date7")
    private String courseDate7;

    @ExcelCellName("Mle7")
    private String instructorNumber7;

    @ExcelCellName("Butée 7")
    private String validityEnd7;

    @ExcelCellName("date8")
    private String courseDate8;

    @ExcelCellName("Mle8")
    private String instructorNumber8;

    @ExcelCellName("Butée 8")
    private String validityEnd8;

    @Override
    public List<Row> getRows() {
        return getRows(8, this.getClass());
    }
}
