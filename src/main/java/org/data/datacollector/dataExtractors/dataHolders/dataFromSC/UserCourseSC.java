package org.data.datacollector.dataExtractors.dataHolders.dataFromSC;

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
public class UserCourseSC extends UserCourseData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("Dte CTL1")
    private String courseDate1;

    @ExcelCellName("Mle1")
    private String instructorNumber1;

    @ExcelCellName("Butée Sc1")
    private String validityEnd1;

    @ExcelCellName("Dte CTL2")
    private String courseDate2;

    @ExcelCellName("Mle2")
    private String instructorNumber2;

    @ExcelCellName("Butée Sc2")
    private String validityEnd2;

    @ExcelCellName("Dte CTL3")
    private String courseDate3;

    @ExcelCellName("Mle3")
    private String instructorNumber3;

    @ExcelCellName("Butée Sc3")
    private String validityEnd3;

    @ExcelCellName("Dte CTL4")
    private String courseDate4;

    @ExcelCellName("Mle4")
    private String instructorNumber4;

    @ExcelCellName("Butée Sc4")
    private String validityEnd4;

    @Override
    public List<Row> getRows() {
        return getRows(4, this.getClass());
    }
}
