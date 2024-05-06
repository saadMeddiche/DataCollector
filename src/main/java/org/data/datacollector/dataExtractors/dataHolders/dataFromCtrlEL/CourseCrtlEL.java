package org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromDG.CourseDG;
import org.data.datacollector.dataExtractors.global.CourseData;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCrtlEL extends CourseData {

    @ExcelCellName("MATR")
    private Long employeeNumber;

    @ExcelCellName("Dte CTL1")
    private String courseDate1;

    @ExcelCellName("Mle1")
    private String instructorNumber1;

    @ExcelCellName("Dte CTL2")
    private String courseDate2;

    @ExcelCellName("Mle2")
    private String instructorNumber2;

    @ExcelCellName("Dte CTL3")
    private String courseDate3;

    @ExcelCellName("Mle3")
    private String instructorNumber3;

    @ExcelCellName("Dte CTL4")
    private String courseDate4;

    @ExcelCellName("Mle4")
    private String instructorNumber4;


    @Override
    public List<Row> getRows() {
        return getRows(4, CourseCrtlEL.class);
    }
}
